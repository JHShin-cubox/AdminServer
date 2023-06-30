package com.adminserver.service;

import com.adminserver.dto.DeviceDTO;
import com.adminserver.dto.EquipmentDTO;
import com.adminserver.dto.SearchDto;
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
    public Integer getXrayCount(SearchDto searchDto){
        return equipmentMapper.getXrayCount(searchDto);
    }
    public Page<EquipmentDTO> getXrayList(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<EquipmentDTO> list = equipmentMapper.getXrayList(searchDto);
        return new PageImpl<>(list, pageable, list.size());
    }

    public Integer getViewerCount(SearchDto searchDto){
        return equipmentMapper.getViewerCount(searchDto);
    }

    public Page<EquipmentDTO> getViewerList(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<EquipmentDTO> list = equipmentMapper.getViewerList(searchDto);
        return new PageImpl<>(list, pageable, list.size());
    }
}
