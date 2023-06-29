package com.adminserver.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LoginHistoryDTO {
    private Long id;
    private String userId;
    private String ip;
    private String regDate;
}
