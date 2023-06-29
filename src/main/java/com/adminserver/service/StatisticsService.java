package com.adminserver.service;

import com.adminserver.dto.UserInfoDTO;
import com.adminserver.dto.XrayStatisticDTO;
import com.adminserver.mapper.OperationManagementMapper;
import com.adminserver.mapper.StatisticsMapper;
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
public class StatisticsService {
    private final StatisticsMapper statisticsMapper;

    public List<XrayStatisticDTO> getXrayStatisticsList() {
        List<XrayStatisticDTO> list = statisticsMapper.getXrayStatistic();
        return list;
    }

    public List<XrayStatisticDTO> getXrayChart(){
        List<XrayStatisticDTO> chartList = statisticsMapper.getXrayChart();
        return chartList;
    }
    public Integer getXrayStatisticsCount(){
        return statisticsMapper.getXrayStatisticsCount();
    }

}
