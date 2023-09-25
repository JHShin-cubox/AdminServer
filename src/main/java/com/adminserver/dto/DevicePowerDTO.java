package com.adminserver.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DevicePowerDTO {
    private Long id;
    private String deviceId;
    private String deviceName;
    private String status;
    private String regDate;
}
