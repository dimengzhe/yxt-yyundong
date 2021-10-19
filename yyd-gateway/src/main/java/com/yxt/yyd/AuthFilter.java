package com.yxt.yyd;

import com.alibaba.fastjson.JSON;
import com.yxt.yyd.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author dimengzhe
 * @date 2020/12/2 9:52
 * @description 网关鉴权
 * 1.某些接口不需要不进行登录验证，如登录，注册，获取验证码等接口。(uri白名单)
 *2.某些接口需要登录验证，但是不需要刷新token有效时间，如客户端轮询请求的接口。
 *3.特定场景下IP黑、白名单。
 *4.处于安全考虑的接口流量控制。
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);
    //过期时间设置为4小时
    private final static long EXPIRE_TIME = Constants.TOKEN_EXPIRE * 60;
    private final static long EXPIRE_TIME_APP = Constants.TOKEN_EXPIRE_APP * 60;

    // 排除过滤的 uri 地址，nacos自行添加
    @Autowired
    private IgnoreWhiteProperties ignoreWhite;
    @Autowired
    private RedisUtil redisUtil;
    /*
    redis中数据存储结构为两个键值对
           键为用户ID，值为用户token，可以通过用户ID查询用户token，实现立刻失效用户token功能。
            键为用户token，值为用户数据，实现token有效性，用户数据缓存功能。     
    */
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        //1.uri白名单。  跳过不需要验证的路径
        if (StringUtils.matches(url, ignoreWhite.getWhites())) {
            return chain.filter(exchange);
        }else if(StringUtils.matchesTwo(url, ignoreWhite.getWhitesTwo())){
            return chain.filter(exchange);
        }
        //2.验证有无令牌。 从请求的header中获取token
        String token = getToken(exchange.getRequest());
        if (StringUtils.isBlank(token)) {
            return setUnauthorizedResponse(exchange, "令牌不能为空");
        }
        //3.验证token是否有效。（a.验证token是否合法 b.验证token是否过期）
        //从redis缓存中获取key对应的内容
        String userName = redisUtil.get(token);
        
        if (StringUtils.isBlank(userName)) {
        	
            return setUnauthorizedResponse(exchange, "登录状态已过期");
        }
        //验签：需要验证token中的签名是否与用户sid一致，后台用密钥+userSid+token除签名以外的内容，重新生成签名，与token中的签名进行比较

        //刷新token过期日期
        if(token.contains("App")){
            //redisUtil.set(token, userName, EXPIRE_TIME_APP);
        	redisUtil.expire(token, EXPIRE_TIME_APP);	
        }else{
            //redisUtil.set(token, userName, EXPIRE_TIME);
        	redisUtil.expire(token, EXPIRE_TIME);
        }

        // 在请求中增加用户信息
        ServerHttpRequest mutableReq = exchange.getRequest().mutate()
                .header(CacheConstants.DETAILS_USERNAME, userName).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }
    /**
     * 鉴权异常处理
     * @param exchange
     * @param msg
     * @return
     */
    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, String msg) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);

        log.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(JSON.toJSONBytes(R.fail(msg)));
        }));
    }

    /**
     * 获取请求token
     */
    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(CacheConstants.HEADER);
//        if (StringUtils.isNotEmpty(token) && token.startsWith(CacheConstants.TOKEN_PREFIX)) {
//            token = token.replace(CacheConstants.TOKEN_PREFIX, "");
//        }
        return token;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
