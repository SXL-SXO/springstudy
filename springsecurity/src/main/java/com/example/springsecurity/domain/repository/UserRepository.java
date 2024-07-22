package com.example.springsecurity.domain.repository;

import com.example.springsecurity.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // username 기반으로 존재하는지 여부 검증 jpa
    Boolean existsByUsername(String username);

    // username 이 DB에 존재하는지 jpa
    UserEntity findByUsername(String username);
}
