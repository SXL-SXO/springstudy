package com.example.springsecurity.domain.ssamuser.entity;

import jakarta.persistence.AttributeConverter;

class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole status) {

        return status.getLegacyCode();
    }

    @Override
    public UserRole convertToEntityAttribute(String dbData) {

        return UserRole.ofLegacyCode(dbData);
    }
}