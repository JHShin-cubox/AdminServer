package com.adminserver.service;

import com.adminserver.dto.DailyStatisticDTO;
import com.adminserver.dto.SearchDto;
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

    public List<XrayStatisticDTO> getXrayStatisticsList(SearchDto searchDto) {
        List<XrayStatisticDTO> list = statisticsMapper.getXrayStatistic(searchDto);
        return list;
    }

    public List<XrayStatisticDTO> getXrayChart(SearchDto searchDto){
        List<XrayStatisticDTO> chartList = statisticsMapper.getXrayChart(searchDto);
        return chartList;
    }
    public Integer getXrayStatisticsCount(SearchDto searchDto){
        return statisticsMapper.getXrayStatisticsCount(searchDto);
    }

    public Integer getLuggageCount(SearchDto searchDto){
        return statisticsMapper.getLuggageCount(searchDto);
    }

    public List<DailyStatisticDTO> getDailyStatisticWeek(){return statisticsMapper.getDailyStatisticWeek();}
    public List<DailyStatisticDTO> getDailyStatisticMonth(){return statisticsMapper.getDailyStatisticMonth();}
    public List<DailyStatisticDTO> getDailyStatisticYear(){return statisticsMapper.getDailyStatisticYear();}

}
