/*==================================================================
프로젝트명 : 통합 관리시스템
작성지 : 신정호
작성일 : 2023년 11월 22일
용도 : 이력 관리 서비스
==================================================================*/

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
    Integer totalCount;
    public Page<XrayImageDTO> getXrayImages(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<XrayImageDTO> list = recordManagementMapper.getXrayImages(searchDto);
        totalCount = recordManagementMapper.getXrayImagesCount(searchDto);
        return new PageImpl<>(list, pageable, totalCount);
    }
    public List<XrayImageDTO> excelXrayImages(SearchDto searchDto){
        List<XrayImageDTO> list = recordManagementMapper.getXrayImages(searchDto);
        return list;
    }

    public Page<DevicePowerDTO> getXrayPowerLog(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<DevicePowerDTO> list = recordManagementMapper.getXrayPowerLog(searchDto);
        totalCount = recordManagementMapper.getXrayPowerLogCount(searchDto);
        return new PageImpl<>(list, pageable, totalCount);
    }
    public Page<DevicePowerDTO> getViewerPowerLog(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<DevicePowerDTO> list = recordManagementMapper.getViewerPowerLog(searchDto);
        totalCount = recordManagementMapper.getViewerPowerLogCount(searchDto);
        return new PageImpl<>(list, pageable, totalCount);
    }

    public Page<DevicePowerDTO> getTrsPowerLog(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<DevicePowerDTO> list = recordManagementMapper.getTrsPowerLog(searchDto);
        totalCount = recordManagementMapper.getTrsPowerLogCount(searchDto);
        return new PageImpl<>(list, pageable, totalCount);
    }

    public Page<LoginHistoryDTO> getLoginLog(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<LoginHistoryDTO> list = recordManagementMapper.getLoginLog(searchDto);
        totalCount = recordManagementMapper.getLoginLogCount(searchDto);
        return new PageImpl<>(list, pageable, totalCount);
    }
    public Page<ActionHistoryDTO> getActionLog(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<ActionHistoryDTO> list = recordManagementMapper.getActionLog(searchDto);
        totalCount = recordManagementMapper.getActionLogCount(searchDto);
        return new PageImpl<>(list, pageable, totalCount);
    }

    public Page<LuggageLogDTO> getLuggageLog(Pageable pageable, SearchDto searchDto) {
        searchDto.setOffset(pageable.getOffset());
        searchDto.setPageSize(pageable.getPageSize());
        List<LuggageLogDTO> list = recordManagementMapper.getLuggageLog(searchDto);
        totalCount = recordManagementMapper.getLuggageCount(searchDto);
        return new PageImpl<>(list, pageable, totalCount);
    }

    public List<XrayImageDTO> getXraySubList(String luggageId){
        return recordManagementMapper.getXraySubImages(luggageId);
    }
    public Integer getXrayImagesCount(SearchDto searchDto){ return recordManagementMapper.getXrayImagesCount(searchDto); }
    public Integer getXrayPowerCount(SearchDto searchDto){ return recordManagementMapper.getXrayPowerLogCount(searchDto); }
    public Integer getViewerPowerCount(SearchDto searchDto){ return recordManagementMapper.getViewerPowerLogCount(searchDto); }
    public Integer getTrsPowerCount(SearchDto searchDto){ return recordManagementMapper.getTrsPowerLogCount(searchDto); }
    public Integer getLoginLogCount(SearchDto searchDto){ return recordManagementMapper.getLoginLogCount(searchDto); }
    public Integer getActionLogCount(SearchDto searchDto){ return recordManagementMapper.getActionLogCount(searchDto); }
    public Integer getLuggageLogCount(SearchDto searchDto){ return recordManagementMapper.getLuggageCount(searchDto); }
    public String duplicateCheck(String imageName){
        return recordManagementMapper.duplicateCheck(imageName);
    }
    public Integer createImage(String imageName, String luggageId){
        return recordManagementMapper.createImage(imageName,luggageId);
    }
    public Integer insertLoginHistory(LoginHistoryDTO loginHistoryDTO){ return recordManagementMapper.insertLoginHistory(loginHistoryDTO);}
    public Integer insertActionHistory(ActionHistoryDTO actionHistoryDTO){ return recordManagementMapper.insertActionHistory(actionHistoryDTO);}

    public List<LuggageLogDTO> excelLuggageList(SearchDto searchDto){return recordManagementMapper.getLuggageLog(searchDto);}
    public List<DevicePowerDTO> excelPowerXrayList(SearchDto searchDto){return recordManagementMapper.getXrayPowerLog(searchDto);}
    public List<DevicePowerDTO> excelPowerViewerList(SearchDto searchDto){return recordManagementMapper.getViewerPowerLog(searchDto);}
    public List<DevicePowerDTO> excelPowerTrsList(SearchDto searchDto){return recordManagementMapper.getTrsPowerLog(searchDto);}
    public List<LoginHistoryDTO> excelLoginList(SearchDto searchDto){return recordManagementMapper.getLoginLog(searchDto);}
    public List<ActionHistoryDTO> excelActionList(SearchDto searchDto){return recordManagementMapper.getActionLog(searchDto);}

    public String getLabelName(Long labelId){return recordManagementMapper.getLabelName(labelId);}
}
