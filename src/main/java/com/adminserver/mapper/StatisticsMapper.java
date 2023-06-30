package com.adminserver.mapper;

import com.adminserver.dto.SearchDto;
import com.adminserver.dto.UserInfoDTO;
import com.adminserver.dto.XrayStatisticDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StatisticsMapper {
    List<XrayStatisticDTO> getXrayStatistic(SearchDto searchDto);

    List<XrayStatisticDTO> getXrayChart(SearchDto searchDto);
    Integer getXrayStatisticsCount(SearchDto searchDto);
}
