package com.adminserver.mapper;

import com.adminserver.dto.DeviceDTO;
import com.adminserver.dto.EquipmentDTO;
import com.adminserver.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EquipmentMapper {
    List<EquipmentDTO> getXrayList();
    Integer getXrayCount();

    List<EquipmentDTO> getViewerList();
    Integer getViewerCount();

    Integer modifyViewer(EquipmentDTO equipmentDTO);
    Integer modifyXray(EquipmentDTO equipmentDTO);
}
