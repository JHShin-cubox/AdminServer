

package com.adminserver.controller;

import com.adminserver.dto.*;
import com.adminserver.entity.UserEntity;
import com.adminserver.security.TokenProvider;
import com.adminserver.service.OperationManagementService;
import com.adminserver.service.RecordManagementService;
import com.adminserver.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RecordManagementService recordManagementService;
    private final OperationManagementService operationManagementService;

    /**
     * 로그인
     */
    @PostMapping("/signin")
    @ApiOperation(value = "로그인", response = ResponseDTO.class)
    public ResponseEntity<?> signInUser(@RequestBody RequestAuthDTO userDTO, HttpServletRequest request) {
        try {
            String userName = operationManagementService.getUserName(userDTO.getUserId());
            UserEntity user = UserEntity.builder()
                    .userId(userDTO.getUserId())
                    .name(userName)
                    .userPassword(userDTO.getUserPassword())
                    .build();
            String clientIp = getClientIP(request);
            LoginHistoryDTO historyDTO = LoginHistoryDTO.builder()
                    .userId(userDTO.getUserId())
                    .ip(clientIp)
                    .build();
            TokenDTO tokens = userService.authenticate(user);
            recordManagementService.insertLoginHistory(historyDTO);
            // 쿠키에 토큰을 담아 response에 전달
            return ResponseEntity.status(HttpStatus.OK)
                    .header(HttpHeaders.SET_COOKIE,
                            ResponseCookie.from("refreshToken", tokens.getRefreshToken())
                                    .maxAge(7 * 24 * 60 * 60)
                                    .path("/")
                                    .httpOnly(true)
                                    .build().toString())
                    .header(HttpHeaders.SET_COOKIE,
                            ResponseCookie.from("accessToken", tokens.getAccessToken())
                                    .maxAge(7 * 24 * 60 * 60)
                                    .path("/")
                                    .httpOnly(true)
                                    .build().toString())
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.OK.value())
                            .data(List.of(userDTO.getUserId()))
                            .build());

        } catch (Exception e) {
            // 실패하면 에러메시지를 리턴

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseDTO.builder()
                    .message(e.getMessage().equals("Password is Wrong") ? "비밀번호를 확인 해주세요" : "아이디를 확인 해주세요")
                    .status(HttpStatus.NOT_FOUND.value())
                    .data(List.of())
                    .build());

        }
    }
    private String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) ip = request.getHeader("Proxy-Client-IP");
        if (ip == null) ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip == null) ip = request.getHeader("HTTP_CLIENT_IP");
        if (ip == null) ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (ip == null) ip = request.getRemoteAddr();
        return ip;
    }


    /**
     * 로그아웃
     */
    @GetMapping("/signout")
    @ApiOperation(value = "로그아웃")
    public ResponseEntity<?> signOutUser(HttpServletResponse response) {
        // maxAge를 0으로 만들어 쿠키에 저장된 토큰 삭제
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, ResponseCookie.from("accessToken", "")
                        .path("/")
                        .maxAge(0)
                        .build().toString())
                .header(HttpHeaders.SET_COOKIE, ResponseCookie.from("refreshToken", "")
                        .path("/")
                        .maxAge(0)
                        .build().toString())
                .body(ResponseDTO.builder()
                        .status(HttpStatus.OK.value())
                        .data(List.of())
                        .build());
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(URI.create("/login"));
//        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    /*
    /**
     * 토큰 재발급
     *
    @PostMapping("/refresh")
    @ApiOperation(value = "토큰 재발급")
    public ResponseEntity<?> refreshToken(@RequestBody RequestAuthDTO authDTO) {
        UserEntity entity = userService.getUserByUserId(authDTO.getUserId());
        // TODO 쿠키에서 refreshtoken을 가져와서 값이 없으면 refreshtoken과 accesstoken을 재발급
        String token = tokenProvider.createAccessToken(authDTO.getUserId());
        entity.setUserToken(token);
        userService.save(entity);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, ResponseCookie.from("accessToken", "")
                        .domain(domain)
                        .path("/")
                        .maxAge(0)
                        .build().toString())
                .header(HttpHeaders.SET_COOKIE,
                        ResponseCookie.from("refreshToken", token)
                                .maxAge(7 * 24 * 60 * 60)
                                .domain(domain)
                                .path("/")
                                .httpOnly(true)
                                .build().toString())
                .body(ResponseDTO.builder()
                        .status(HttpStatus.OK.value())
                        .data(List.of())
                        .build());
    }*/

}

