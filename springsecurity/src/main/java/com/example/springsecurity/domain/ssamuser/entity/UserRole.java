package com.example.springsecurity.domain.ssamuser.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum UserRole {
    STUDENT("STUDENT", "1"),
    TEACHER("TEACHER", "2"),
    MANAGER("MANAGER", "3");


    private String desc;
    private String legacyCode;

    public static UserRole ofLegacyCode(String legacyCode) {
        return Arrays.stream(UserRole.values())
                .filter(v -> v.getLegacyCode().equals(legacyCode))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("상태코드에 legacyCode=[%s] 가 존재하지 않습니다", legacyCode)));
    }
}
