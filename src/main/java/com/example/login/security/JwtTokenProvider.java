package com.example.login.security;

import com.sun.security.auth.UserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    private String jwtSecret;


    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 86400000; // 24小时

    public static String generateToken(String subject, Map<String, Object> userInfo) {

        return Jwts.builder()
                .setClaims(userInfo)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }


    /**
     * 验证Token有效性
     * @param token Token字符串
     * @return 验证结果
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            // 签名异常，Token无效
            System.out.println("无效的Token签名");
        } catch (MalformedJwtException e) {
            // Token格式错误
            System.out.println("无效的Token格式");
        } catch (ExpiredJwtException e) {
            // Token已过期
            System.out.println("Token已过期");
        } catch (UnsupportedJwtException e) {
            // 不支持的Token类型
            System.out.println("不支持的Token类型");
        } catch (IllegalArgumentException e) {
            // 参数错误
            System.out.println("Token参数错误");
        }
        return false;
    }

    /**
     * 从Token中获取用户名
     * @param token Token字符串
     * @return 用户名
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /**
     * 检查Token是否即将过期
     * @param token Token字符串
     * @return 是否即将过期
     */
    public static boolean isTokenExpiringSoon(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        Date expiration = claims.getExpiration();
        // 如果Token将在30分钟内过期，则认为即将过期
        return expiration.getTime() - System.currentTimeMillis() < 1800000;
    }

}
