package com.adminserver.mapper;

import com.adminserver.dto.LabelDTO;
import com.adminserver.dto.SearchDto;
import com.adminserver.dto.SettingDTO;
import com.adminserver.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperationManagementMapper {
    List<UserInfoDTO> getUserList(SearchDto searchDto);

    Integer getUserCount(SearchDto searchDto);

    String getUserName(@Param("userId") String userId);

    String getUserRole(@Param("userId") String userId);

    Integer creteUser(UserInfoDTO userInfoDTO);

    Integer updateUser(UserInfoDTO userInfoDTO);

    String duplicateCheck(@Param("userId") String userId);

    Integer deleteUser(@Param("userId") String userId);

    SettingDTO getSetting();

    String getLabelId(@Param("labelId") Integer labelId);

    void insertLabelClass(LabelDTO labelDto);

    void classFlagChange();
}
