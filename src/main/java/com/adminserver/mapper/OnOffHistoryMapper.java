package com.adminserver.mapper;

import com.adminserver.dto.OnOffHistoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OnOffHistoryMapper {

    List<OnOffHistoryDTO> getAllHistory(@Param("deviceId") Long deviceId, @Param("type") String type);
}
