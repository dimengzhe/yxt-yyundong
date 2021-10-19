package com.yxt.yyd.common.base.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author dimengzhe
 * @date 2021/9/3 16:55
 * @description
 */
@Slf4j
@Data
public class JWTUtil {

    private static final String TOKEN_SECRET = "yXtJLzxh2bGciO5iJIUzI1NiJ9";
    private static final String ISS = "WBK";
    private static final String USERNO = "userNo";
    //    private static final Long TIME = 60 * 1000L; // 1min
    private static final Long TIME = 24 * 3600 * 1000L; // 1天

    //创建Token
    public static String create(String userNo) {
        try {
            return JWT
                    .create()
                    .withIssuer(ISS)
                    .withClaim(USERNO, userNo)
                    .withExpiresAt(new Date(System.currentTimeMillis() + TIME))
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e) {
            log.error("JWT生成失败", e);
            return e.getMessage();
        }
    }

    //解析Token
    public static DecodedJWT verify(String token) throws Exception {
        return JWT
                .require(Algorithm.HMAC256(TOKEN_SECRET))
                .withIssuer(ISS)
                .build()
                .verify(token);
    }

    //根据解析生成的Token返回userNo
    public static Long getUserNo(DecodedJWT decodedJWT) {
        return Long.parseLong(decodedJWT.getClaim(USERNO).asString());
    }

    public static String getUserNo1(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim(USERNO).asString();
    }
}
