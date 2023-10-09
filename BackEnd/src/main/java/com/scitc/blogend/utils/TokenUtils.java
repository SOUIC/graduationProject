package com.scitc.blogend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    //密钥 backup: NFOIWEiej87efFBEHBFWejkbPAcj3jN3
    //请勿泄露
    private static final String SECRET = "NFOIWEiej87efFBEHBFWejkbPAcj3jN3";
    //过期时间 一天
    private static final long timeField = 24 * 60 * 60 * 1000;

    public static String setToken() {
        //设置过期时间
        Date date = new Date(System.currentTimeMillis() + timeField);

        //选择算法
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        //设置头部信息
        //声明类型和算法
        Map<String, Object> header = new HashMap<>(2);
        header.put("Type", "Jwt");
        header.put("alg", "HS256");

        //返回token字符串
        return JWT.create().withHeader(header).withExpiresAt(date).sign(algorithm);
    }

    //验证
    public static void verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
        } catch (Exception e) {

        }

    }
}
