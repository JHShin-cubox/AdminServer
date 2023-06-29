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
