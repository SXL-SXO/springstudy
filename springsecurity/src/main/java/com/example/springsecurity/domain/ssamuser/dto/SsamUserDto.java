package com.example.springsecurity.domain.ssamuser.dto;

import com.example.springsecurity.domain.ssamuser.entity.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class SsamUserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Long school_id;
    private Long board_id;
    private String img_url;
    private UserRole role;
    private LocalDate birth;
    private String other_name;
    private String other_phone;
    private String other_relation;

    private String username;
    private String password;
}
