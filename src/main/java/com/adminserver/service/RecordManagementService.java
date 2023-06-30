package com.adminserver.service;

import com.adminserver.dto.*;
import com.adminserver.mapper.RecordManagementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordManagementService {
    private final RecordManagementMapper recordManagementMapper;

    public Page<XrayImageDTO> getXrayImages(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<XrayImageDTO> list = recordManagementMapper.getXrayImages(searchDto);
        return new PageImpl<>(list, pageable, list.size());
    }

    public Page<DevicePowerDTO> getXrayPowerLog(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<DevicePowerDTO> list = recordManagementMapper.getXrayPowerLog(searchDto);
        return new PageImpl<>(list, pageable, list.size());
    }
    public Page<DevicePowerDTO> getViewerPowerLog(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<DevicePowerDTO> list = recordManagementMapper.getViewerPowerLog(searchDto);
        return new PageImpl<>(list, pageable, list.size());
    }

    public Page<LoginHistoryDTO> getLoginLog(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<LoginHistoryDTO> list = recordManagementMapper.getLoginLog(searchDto);
        return new PageImpl<>(list, pageable, list.size());
    }
    public Page<ActionHistoryDTO> getActionLog(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<ActionHistoryDTO> list = recordManagementMapper.getActionLog(searchDto);
        return new PageImpl<>(list, pageable, list.size());
    }

    public List<XrayImageDTO> getXraySubList(String luggageId){
        return recordManagementMapper.getXraySubImages(luggageId);
    }

    public Integer getXrayImagesCount(SearchDto searchDto){
        return recordManagementMapper.getXrayImagesCount(searchDto);
    }
    public Integer getXrayPowerCount(SearchDto searchDto){ return recordManagementMapper.getXrayPowerLogCount(searchDto); }
    public Integer getViewerPowerCount(SearchDto searchDto){ return recordManagementMapper.getViewerPowerLogCount(searchDto); }
    public Integer getLoginLogCount(SearchDto searchDto){ return recordManagementMapper.getLoginLogCount(searchDto); }
    public Integer getActionLogCount(SearchDto searchDto){ return recordManagementMapper.getActionLogCount(searchDto); }

    public String duplicateCheck(String imageName){
        return recordManagementMapper.duplicateCheck(imageName);
    }
    public Integer createImage(String imageName){
        return recordManagementMapper.createImage(imageName);
    }

    public Integer insertLoginHistory(LoginHistoryDTO loginHistoryDTO){ return recordManagementMapper.insertLoginHistory(loginHistoryDTO);}
    public Integer insertActionHistory(ActionHistoryDTO actionHistoryDTO){ return recordManagementMapper.insertActionHistory(actionHistoryDTO);}
}
