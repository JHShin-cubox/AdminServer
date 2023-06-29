package com.adminserver.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenProvider implements HandlerInterceptor {

    private static final String SECRET_KEY = "YxgCkpqLQ3NHaslkdalkdjal1234321XcRfhiPc5isYenAR401234lasdsvzwVcqW2Mpk";
    Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String createAccessToken(String userId, String userName, String userRole) {
        Date expiryDate = Date.from(
                Instant.now()
                        .plus(24, ChronoUnit.HOURS));

        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS512)
                .setIssuer("CUBOX")
                .setClaims(Map.of("userId", userId, "userName", userName, "userRole", userRole))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .compact();
    }

    public String createRefreshToken(String userId, String userName, String userRole) {
        Date expiryDate = Date.from(
                Instant.now()
                        .plus(7, ChronoUnit.DAYS));

        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS512)
                .setIssuer("CUBOX")
                .setClaims(Map.of("userId", userId, "userName", userName, "userRole", userRole))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .compact();
    }

    public String validateAndGetUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("userId", String.class);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refreshToken")) {
                    String token = cookie.getValue();
                    try {
                        Jws<Claims> claims = Jwts.parser()
                                .setSigningKey(key) // JWT를 서명할 때 사용한 비밀키를 설정합니다.
                                .parseClaimsJws(token);

                        Claims body = claims.getBody();
                        String userName = body.get("userName", String.class);
                        String userId = body.get("userId", String.class);
                        String userRole = body.get("userRole", String.class);
                        request.setAttribute("userName", userName);
                        request.setAttribute("userId", userId);
                        request.setAttribute("userRole", userRole);
                    } catch (Exception e) {
                        // JWT 디코드 중 에러가 발생한 경우 처리
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        return true; // 요청 계속 진행
    }

}
