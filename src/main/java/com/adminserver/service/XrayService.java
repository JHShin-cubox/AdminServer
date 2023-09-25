package com.adminserver.service;

import com.adminserver.dto.CountLabelIdDTO;
import com.adminserver.dto.XrayStatDTO;
import com.adminserver.dto.XrayStatisticDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface XrayService {
    Page<XrayStatisticDTO> getAllStatistics(Optional<Integer> page, Pageable pageable);
    Integer getStatCount();




}
