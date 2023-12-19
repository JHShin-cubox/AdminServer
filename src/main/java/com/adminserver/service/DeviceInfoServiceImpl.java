/*==================================================================
프로젝트명 : 통합 관리시스템
작성지 : 신정호
작성일 : 2023년 11월 22일
용도 : 장비 관리 서비스
==================================================================*/


package com.adminserver.service;

import com.adminserver.dto.DeviceDTO;
import com.adminserver.mapper.DeviceInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DeviceInfoServiceImpl implements DeviceInfoService {

    private final DeviceInfoMapper pcStatusMapper;

    @Override
    public List<DeviceDTO> getViewerInfo() {
        return pcStatusMapper.getViewerInfo();
    }

    @Override
    public List<DeviceDTO> getXrayInfo() {
        return pcStatusMapper.getXrayInfo();
    }

    @Override
    public List<DeviceDTO> getHistorySel() {
        return pcStatusMapper.getHistorySel();
    }
}
