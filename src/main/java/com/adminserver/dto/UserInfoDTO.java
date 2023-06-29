package com.adminserver.dto;

import com.adminserver.entity.UserRole;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserInfoDTO {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String role;
    private String department;
    private String rank;
    private String phone;
    private String email;
    private String regDate;
    private String modifiedDate;
    private String actionType;
}
