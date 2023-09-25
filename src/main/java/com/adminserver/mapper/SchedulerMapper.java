package com.adminserver.mapper;

import com.adminserver.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SchedulerMapper {
    void InsertDailyStatistic();
}
