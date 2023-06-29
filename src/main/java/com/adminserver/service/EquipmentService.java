package com.adminserver.service;

import com.adminserver.dto.DeviceDTO;
import com.adminserver.dto.EquipmentDTO;
import com.adminserver.dto.UserInfoDTO;
import com.adminserver.mapper.EquipmentMapper;
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
public class EquipmentService {
    private final EquipmentMapper equipmentMapper;
    public Integer getXrayCount(){
        return equipmentMapper.getXrayCount();
    }

    public Page<EquipmentDTO> getXrayList(Optional<Integer> page, Pageable pageable) {
        List<EquipmentDTO> xrayList = equipmentMapper.getXrayList();
        int start = Math.toIntExact(pageable.getOffset());
        int end = Math.min((start + pageable.getPageSize()),xrayList.size());
        return new PageImpl<>(xrayList.subList(start, end), pageable, xrayList.size());
    }

    public Integer getViewerCount(){
        return equipmentMapper.getViewerCount();
    }

    public Page<EquipmentDTO> getViewerList(Optional<Integer> page, Pageable pageable) {
        List<EquipmentDTO> viewerList = equipmentMapper.getViewerList();
        int start = Math.toIntExact(pageable.getOffset());
        int end = Math.min((start + pageable.getPageSize()),viewerList.size());
        return new PageImpl<>(viewerList.subList(start, end), pageable, viewerList.size());
    }
    public void modifyViewer(EquipmentDTO equipmentDTO){equipmentMapper.modifyViewer(equipmentDTO); }
    public void modifyXray(EquipmentDTO equipmentDTO){equipmentMapper.modifyXray(equipmentDTO); }
}
