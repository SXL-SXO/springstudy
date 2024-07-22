package com.example.springsecurity.global.config;

import com.example.springsecurity.global.jwt.JwtUtil;
import com.example.springsecurity.global.jwt.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 시큐리티 설정을 위한
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //해쉬를 암호화해 진행하기 위해서 필요한 것들

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //csrf disable
        http.csrf((auth) -> auth.disable());
        // 세션방식에서는 세션이 고정이 되기 때문에 csrf 방어 필수
        // 벗 얘는 stateless라서 신경 안써도 됨

        //formlogin, httpbasic disable
        http.formLogin((auth) -> auth.disable());
        http.httpBasic((auth) -> auth.disable());
        // jwt방식을 활용하기 때문에 신경 안써도 됨

        // 경로에 대한 인가작업
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/login", "/", "join").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated());
        // "/login", "/join", "/" 에 대해서는 전부 접근 허용
        // "/admin" 에 대해서는 관리자 권한을 가진 사람만 접근 허용
        // 다른 요청에 대해서는 로그인한 사용자만 접근허용

        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);
        // Login filter 배우면서 추가
        // addFilterAt : 그 위치에, addFilterBefore : 전에, addFilterAtfer : 이후에
        // 여기선 뭐를 대신하도록 딱 한거여서 At 사용
        // LoginFilter() : 생성자로 주입받아서 특정인자 필요 -> bean으로 등록한 authenticationManager 입력
        // authenticationManager() : security config에서 주입받아서 해야함

        // 세션 설정
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // 얘가 가장 중요한 부분

        return http.build();
    }
}
