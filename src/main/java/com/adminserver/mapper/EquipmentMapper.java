package com.adminserver.mapper;

import com.adminserver.dto.DeviceDTO;
import com.adminserver.dto.EquipmentDTO;
import com.adminserver.dto.SearchDto;
import com.adminserver.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EquipmentMapper {
    List<EquipmentDTO> getXrayList(SearchDto searchDto);
    Integer getXrayCount(SearchDto searchDto);

    List<EquipmentDTO> getViewerList(SearchDto searchDto);
    Integer getViewerCount(SearchDto searchDto);

    List<EquipmentDTO> getTrsList(SearchDto searchDto);
    Integer getTrsCount(SearchDto searchDto);
}
