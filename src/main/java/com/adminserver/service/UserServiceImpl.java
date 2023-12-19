/*==================================================================
프로젝트명 : 통합 관리시스템
작성지 : 신정호
작성일 : 2023년 11월 22일
용도 : 사용자 관리 서비스
==================================================================*/

package com.adminserver.service;

import com.adminserver.dto.TokenDTO;
import com.adminserver.entity.UserEntity;
import com.adminserver.entity.UserRepository;
import com.adminserver.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final OperationManagementService operationManagementService;
    private final Logger log = LoggerFactory.getLogger(getClass());


    @Override
    @Transactional
    public UserEntity createUser(final UserEntity userEntity) {

        final String userId = userEntity.getUserId();
        if(userRepository.existsByUserId(userId)){
            log.warn("User ID Already Exists");
            throw new RuntimeException("User Id Already Exists");
        }

        return userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public TokenDTO authenticate(final UserEntity userEntity) {

        // id와 비밀번호 체크
        UserEntity originalUser = userRepository.findByUserId(userEntity.getUserId())
                .orElseThrow(() -> {
                    throw new RuntimeException("Cannot Find User");
        });

        if(originalUser != null && passwordEncoder.matches(userEntity.getUserPassword(),
                                                            originalUser.getUserPassword())){
            // 토큰발급
            String userName = operationManagementService.getUserName(originalUser.getUserId());
            String userRole = operationManagementService.getUserRole(originalUser.getUserId());
            String accessToken = tokenProvider.createAccessToken(originalUser.getUserId(), userName, userRole);
            String refreshToken = tokenProvider.createRefreshToken(originalUser.getUserId(), userName, userRole);

            TokenDTO tokenDTO = TokenDTO.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

            originalUser.setUserToken(refreshToken);
            userRepository.save(originalUser);
            return tokenDTO;

       } else {
            throw new RuntimeException("Password is Wrong");
       }
    }

    @Transactional
    @Override
    public UserEntity save(UserEntity userEntity) {
        if(userEntity == null){
            throw new RuntimeException("database insert or update failure");
        }
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserByUserId(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> {
            throw new RuntimeException("Cannot Find User");
        });
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.OrderByUserIdxDesc();
    }


    @Override
    public UserEntity getUserByUserIdx(Long userIdx) {
        return userRepository.findByUserIdx(userIdx).orElseThrow(() -> {
            throw new RuntimeException("Cannot Find User");
        });
    }


}
