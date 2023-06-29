package com.adminserver.service;

import com.adminserver.dto.UserInfoDTO;
import com.adminserver.mapper.OperationManagementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationManagementService {
    private final OperationManagementMapper managementMapper;

    public Page<UserInfoDTO> getUserList(Optional<Integer> page, Pageable pageable) {
        List<UserInfoDTO> userList = managementMapper.getUserList();

        int start = Math.toIntExact(pageable.getOffset());
        int end = Math.min((start + pageable.getPageSize()),userList.size());
        return new PageImpl<>(userList.subList(start, end), pageable, userList.size());
    }
    public Integer getUserCount(){
        return managementMapper.getUserCount();
    }

    public String getUserName(String userId){ return managementMapper.getUserName(userId);}
    public String getUserRole(String userId){ return managementMapper.getUserRole(userId);}
    @Transactional
    public String createUser(UserInfoDTO userInfoDTO){
        Integer result = managementMapper.creteUser(userInfoDTO);
        return userInfoDTO.getUserId();
    }

    @Transactional
    public void updateUser(UserInfoDTO userInfoDTO){
        Integer result = managementMapper.updateUser(userInfoDTO);
        managementMapper.updateUser(userInfoDTO);
    }
    @Transactional
    public String duplicateCheck(String userId){
        String result = managementMapper.duplicateCheck(userId);
        return result;
    }

    @Transactional
    public void deleteUser(String userId){
        managementMapper.deleteUser(userId);
    }
}
