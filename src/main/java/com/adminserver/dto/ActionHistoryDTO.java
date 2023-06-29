package com.adminserver.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ActionHistoryDTO {
    private Long id;
    private String userId;
    private String mainMenu;
    private String subMenu;
    private String type;
    private String regDate;
}
