package com.example.springsecurity.domain.ssamuser.entity;

import com.example.springsecurity.domain.ssamuser.dto.SsamUserDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class SsamUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "uesr_name", length = 22)
    private String name;

    @Column(name = "user_email", length = 45)
    private String email;

    @Column(name = "user_phone", length = 11)
    private String phone;

    @Column
    private Long school_id;

    @Column
    private Long board_id;

    @Column(name = "user_img_url", length = 255)
    private String img_url;

    @Column
    private UserRole role;

    @Column(columnDefinition = "DATE")
    private LocalDate birth;

    @Column(length = 22)
    private String other_name;

    @Column(length = 11)
    private String other_phone;

    @Column(length = 1)
    private String other_relation;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    public SsamUserDto toSsamUserDto(SsamUserEntity ssamUserEntity) {
        return SsamUserDto.builder()
                .id(ssamUserEntity.id)
                .name(ssamUserEntity.name)
                .email(ssamUserEntity.email)
                .phone(ssamUserEntity.phone)
                .school_id(ssamUserEntity.school_id)
                .board_id(ssamUserEntity.board_id)
                .img_url(ssamUserEntity.img_url)
                .role(ssamUserEntity.role)
                .birth(ssamUserEntity.birth)
                .other_name(ssamUserEntity.other_name)
                .other_phone(ssamUserEntity.other_phone)
                .other_relation(ssamUserEntity.other_relation)
                .username(ssamUserEntity.username)
                .password(ssamUserEntity.password)
                .build();
    }

    public SsamUserEntity toSsamUserEntity(SsamUserDto ssamUserDto) {
        return SsamUserEntity.builder()
                .id(ssamUserDto.getId())
                .name(ssamUserDto.getName())
                .email(ssamUserDto.getEmail())
                .phone(ssamUserDto.getPhone())
                .school_id(ssamUserDto.getSchool_id())
                .board_id(ssamUserDto.getBoard_id())
                .img_url(ssamUserDto.getImg_url())
                .role(ssamUserDto.getRole())
                .birth(ssamUserDto.getBirth())
                .other_name(ssamUserDto.getOther_name())
                .other_phone(ssamUserDto.getOther_phone())
                .other_relation(ssamUserDto.getOther_relation())
                .username(ssamUserDto.getUsername())
                .password(ssamUserDto.getPassword())
                .build();
    }


}
