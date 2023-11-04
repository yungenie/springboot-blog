package com.yunjin.springbootblog.config;


import com.yunjin.springbootblog.config.jwt.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * AccessToken 값이 담긴 Authorization 헤더값을 가져온 뒤 AccessToken 유효하다면 인증 정보를 설정함.
 */
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // 요청 헤더의 Authorization 키의 값 조회
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        // 가져온 값의 접두사 제거
        String token = getAccessToken(authorizationHeader);
        // 가져온 토큰이 유효한지 확인하고, 유효한 때는 인증 정보를 설정
        if (tokenProvider.validToken(token)) {
            //
            Authentication authentication = tokenProvider.getAuthentication(token);
            // SecurityContextHolder는 시큐리티 컨텍스트 객체를 저장하는 객체 (시큐리티 컨텍스트 : 인증 객체가 저장되는 보관소)
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 필터는 각종 요청을 처리하기 위한 로직으로 URL 패턴에 맞는 모든 요청을 처리하는 기능을 제공
        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

}
