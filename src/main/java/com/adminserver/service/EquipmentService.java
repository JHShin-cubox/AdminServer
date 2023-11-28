package com.adminserver.service;

import com.adminserver.dto.*;
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
    Integer totalCount;
    public Integer getXrayCount(SearchDto searchDto){
        return equipmentMapper.getXrayCount(searchDto);
    }
    public Page<EquipmentDTO> getXrayList(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<EquipmentDTO> list = equipmentMapper.getXrayList(searchDto);
        totalCount = equipmentMapper.getXrayCount(searchDto);
        return new PageImpl<>(list, pageable, totalCount);
    }


    public Page<EquipmentDTO> getViewerList(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<EquipmentDTO> list = equipmentMapper.getViewerList(searchDto);
        totalCount = equipmentMapper.getViewerCount(searchDto);
        return new PageImpl<>(list, pageable, totalCount);
    }
    public Integer getViewerCount(SearchDto searchDto){ return equipmentMapper.getViewerCount(searchDto); }


    public Page<EquipmentDTO> getTrsList(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<EquipmentDTO> list = equipmentMapper.getTrsList(searchDto);
        totalCount = equipmentMapper.getTrsCount(searchDto);
        return new PageImpl<>(list, pageable, totalCount);
    }
    public Integer getTrsCount(SearchDto searchDto){ return equipmentMapper.getTrsCount(searchDto); }


    public List<EquipmentDTO> excelEquipXrayList(SearchDto searchDto){return equipmentMapper.getXrayList(searchDto);}
    public List<EquipmentDTO> excelEquipViewerList(SearchDto searchDto){return equipmentMapper.getViewerList(searchDto);}
    public List<EquipmentDTO> excelEquipTrsList(SearchDto searchDto){return equipmentMapper.getTrsList(searchDto);}

}
