package com.example.springsecurity.domain.service;

import com.example.springsecurity.domain.repository.UserRepository;
import com.example.springsecurity.domain.dto.JoinDTO;
import com.example.springsecurity.domain.entity.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO) {
        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        boolean ifExist = userRepository.existsByUsername(username);

        if(ifExist) return;

        UserEntity data = new UserEntity();
        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password)) ;
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }
}
