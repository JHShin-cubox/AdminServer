package com.adminserver.service;

import com.adminserver.dto.DeviceDTO;

import java.util.List;

public interface DeviceInfoService {
   List<DeviceDTO> getViewerInfo();
   List<DeviceDTO> getXrayInfo();

   List<DeviceDTO> getHistorySel();
}
