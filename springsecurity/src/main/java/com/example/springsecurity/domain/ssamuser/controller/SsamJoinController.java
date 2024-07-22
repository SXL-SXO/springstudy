package com.example.springsecurity.domain.ssamuser.controller;

import com.example.springsecurity.global.dto.CommonResponseDto;
import com.example.springsecurity.domain.ssamuser.dto.SsamUserDto;
import com.example.springsecurity.domain.ssamuser.service.SsamJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class SsamJoinController {
    private final SsamJoinService ssamJoinService;
    @GetMapping("/join")
    public ResponseEntity<CommonResponseDto> join(SsamUserDto ssamMemberDto) {
        return ResponseEntity.ok(ssamJoinService.ssamJoinProcess(ssamMemberDto));
    }
}
