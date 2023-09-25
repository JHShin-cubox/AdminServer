package com.adminserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DailyStatisticDTO {
    private String passengerCount;
    private String detectionCount;
    private String passCount;
    private String checkCount;
    private String regDate;
}
