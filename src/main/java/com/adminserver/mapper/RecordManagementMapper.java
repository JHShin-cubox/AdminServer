package com.adminserver.mapper;

import com.adminserver.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface RecordManagementMapper {
    List<XrayImageDTO> getXrayImages(SearchDto searchDto);

    List<XrayImageDTO> getXraySubImages(@Param("luggageId") String luggageId);
    Integer getXrayImagesCount(SearchDto searchDto);

    List<DevicePowerDTO> getXrayPowerLog(SearchDto searchDto);
    List<DevicePowerDTO> getViewerPowerLog(SearchDto searchDto);

    List<LoginHistoryDTO> getLoginLog(SearchDto searchDto);
    List<ActionHistoryDTO> getActionLog(SearchDto searchDto);
    Integer getXrayPowerLogCount(SearchDto searchDto);
    Integer getViewerPowerLogCount(SearchDto searchDto);

    Integer getLoginLogCount(SearchDto searchDto);

    Integer getActionLogCount(SearchDto searchDto);

    Integer createImage(@Param("imageName") String imageName, @Param("luggageId") String luggageId);
    String duplicateCheck(@Param("imageName")String imageName);

    Integer insertLoginHistory(LoginHistoryDTO loginHistoryDTO);

    Integer insertActionHistory(ActionHistoryDTO actionHistoryDTO);
}
