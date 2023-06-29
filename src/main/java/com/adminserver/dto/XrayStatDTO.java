package com.adminserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface XrayStatDTO {
    String getXray_name();
    String getLabel_id();
    String getLabel_name();
    String getScore();
    int getAmount();
    Double getPercentage();
}
