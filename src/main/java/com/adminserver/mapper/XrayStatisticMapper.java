package com.adminserver.mapper;

import com.adminserver.dto.XrayStatisticDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface XrayStatisticMapper {

    List<XrayStatisticDTO> getXrayStatistic();
}
