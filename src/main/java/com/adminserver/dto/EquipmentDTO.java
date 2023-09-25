package com.adminserver.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EquipmentDTO {
    private Long id;
    private String deviceId;
    private String deviceIp;
    private String name;
    private String power;
    private String status;
    private String type;
    private String distributionCount;
    private String location;
    private String regDate;
    private String modifiedDate;
}
