package com.adminserver.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LuggageLogDTO {
    private Long id;
    private String xrayName;
    private String checkName;
    private String userName;
    private String type;
    private String regDate;
}
