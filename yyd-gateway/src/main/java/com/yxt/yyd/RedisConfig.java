package com.yxt.yyd;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author dimengzhe
 * @date 2020/9/18 19:37
 * @description
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int redisPoolMaxActive;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private int redisPoolMaxWait;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int redisPoolMaxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int redisPoolMinIdle;
    @Value("${spring.redis.database}")
    private int database;

    @Bean
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大空闲数
        jedisPoolConfig.setMaxIdle(redisPoolMaxIdle);
        //最小空闲数
        jedisPoolConfig.setMinIdle(redisPoolMinIdle);
        //最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(redisPoolMaxWait);
        //连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(redisPoolMaxActive);
        return jedisPoolConfig;
    }


    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        jedisConnectionFactory.setDatabase(database);
        //IP地址
        jedisConnectionFactory.setHostName(host);
        //如果redis设置有密码
        jedisConnectionFactory.setPassword(password);
        //端口号
        jedisConnectionFactory.setPort(port);
        //客户端超时时间单位是毫秒
        jedisConnectionFactory.setTimeout(timeout);
//        jedisConnectionFactory.afterPropertiesSet();            //记得添加这行！
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        //redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        // redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        // hash的value序列化方式采用jackson
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
