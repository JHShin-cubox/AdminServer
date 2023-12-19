/*==================================================================
프로젝트명 : 통합 관리시스템
작성지 : 신정호
작성일 : 2023년 11월 22일
용도 : Jwt 인증 처리
==================================================================*/

package com.adminserver.security;

import com.adminserver.service.GrantAuthorityService;
import com.adminserver.service.OperationManagementService;
import com.adminserver.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final GrantAuthorityService grantAuthorityService;
    private final OperationManagementService operationManagementService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // log.info("filter is running");
        // accessToken, refreshToken 가져오기
        String accessToken = findAccessToken(request).orElse("");
        String refreshToken = findRefreshToken(request).orElse("");
        String userId = "";
//        log.info("accessToken: {}", accessToken);

        try {
            // 두 토큰이 전부 없을 때
            if(accessToken.equals("") && refreshToken.equals("")){
                throw new RuntimeException("no tokens available");
            }

            // accessToken이 만료되고 refreshToken이 존재하면 accessToken을 재발급한다.
            if (accessToken.equals("")) {
                userId = tokenProvider.validateAndGetUserId(refreshToken);
                String userName = operationManagementService.getUserName(userId);
                String userRole = operationManagementService.getUserRole(userId);
                System.out.println(userName);
                accessToken = tokenProvider.createAccessToken(userId, userName, userRole);
                response.setHeader(HttpHeaders.SET_COOKIE,
                        ResponseCookie.from("accessToken", accessToken)
                                .maxAge(7 * 24 * 60 * 60)
                                .path("/")
                                .httpOnly(true)
                                .build().toString());
            }
            // accessToken이 있으면 검사
            userId = tokenProvider.validateAndGetUserId(accessToken);
//            log.info("user id : " + userId);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userId, null, AuthorityUtils.NO_AUTHORITIES
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {

        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> findRefreshToken(HttpServletRequest request) {
        Cookie refreshTokenCookie = WebUtils.getCookie(request, "refreshToken");
        if (refreshTokenCookie != null) {
            return Optional.ofNullable(refreshTokenCookie.getValue());
        }
        return Optional.empty();
    }

    public Optional<String> findAccessToken(HttpServletRequest request) {
        Cookie accessTokenCookie = WebUtils.getCookie(request, "accessToken");
        if (accessTokenCookie != null) {
            return Optional.ofNullable(accessTokenCookie.getValue());
        }
        return Optional.empty();
    }


}
