package com.example.springsecurity.domain.ssamuser.service;

import com.example.springsecurity.global.dto.CommonResponseDto;
import com.example.springsecurity.domain.ssamuser.dto.SsamUserDto;
import com.example.springsecurity.domain.ssamuser.entity.SsamUserEntity;
import com.example.springsecurity.global.error.exception.DuplicateUserNameException;
import com.example.springsecurity.global.error.ErrorCode;
import com.example.springsecurity.domain.ssamuser.repository.SsamUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SsamJoinService {
    private final SsamUserRepository ssamUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CommonResponseDto ssamJoinProcess(SsamUserDto ssamUserDto){
        String username = ssamUserDto.getUsername();

        if(ssamUserRepository.existsByUsername(username)) throw new DuplicateUserNameException(ErrorCode.DuplicateUserName);

        SsamUserEntity ssamUserEntity = new SsamUserEntity();
        ssamUserEntity.toSsamUserEntity(ssamUserDto);
        ssamUserEntity.setPassword(bCryptPasswordEncoder.encode(ssamUserDto.getPassword()));
        ssamUserRepository.save(ssamUserEntity);
        return new CommonResponseDto("OK");
    }

}
