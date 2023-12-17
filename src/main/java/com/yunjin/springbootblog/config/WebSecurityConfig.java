package com.yunjin.springbootblog.config;

import com.yunjin.springbootblog.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailService userService;

    /**
     * 스프링 시큐리티 기능 비활성화
     * 스프링 시큐리티의 모든 기능을 사용하지 않게 설정하는 코드
     * static 하위 경로와 h2 데이터를 확인하는 대상은 적용하지 않음
     */
    @Bean
    public WebSecurityCustomizer configure() {
        return web -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

    /**
     * 특정 HTTP 요청에 대한 웹 기반 보안 구성
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);

        return http
                .authorizeHttpRequests() // 인증, 인가 설정
                    .requestMatchers(toH2Console()).permitAll()
                    .requestMatchers("/login", "/signup", "/user", "/api/boards").permitAll() // 특정 경로 허용
                    .anyRequest().authenticated()
                .and()
                .formLogin() // 폼 기반 로그인 설정
                    .loginPage("/login")
                    .defaultSuccessUrl("/articles") // 로그인 완료되었을 때 이동 경로
                .and()
                .logout() // 로그아웃 설정
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)
                .and()
                .csrf().disable()
                .requestCache((cache) -> cache
                        .requestCache(requestCache))
                .build();
    }


    /**
     * 인증 관리자 관련 설정
     * 사용자 정보를 가져올 서비스를 재정의하거나, 인증밥법, JDBC 기반 인증 등을 설정할 때 사용합니다.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService) // 사용자 정보 서비스 설정.
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    /**
     * 패스워드 인코더로 사용할 빈 등록
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
