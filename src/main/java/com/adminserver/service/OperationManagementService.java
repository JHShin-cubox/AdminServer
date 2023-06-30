package com.adminserver.service;

import com.adminserver.dto.SearchDto;
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

    public Page<UserInfoDTO> getUserList(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<UserInfoDTO> list = managementMapper.getUserList(searchDto);
        return new PageImpl<>(list, pageable, list.size());
    }
    public Integer getUserCount(SearchDto searchDto){
        return managementMapper.getUserCount(searchDto);
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
