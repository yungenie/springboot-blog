package com.yunjin.springbootblog.service;

import com.yunjin.springbootblog.config.jwt.TokenProvider;
import com.yunjin.springbootblog.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * RefreshToken으로 토큰 유효성 검사를 진행하고, 유효한 토큰일 때 RefreshToken으로 사용자 ID를 찾는다.
 * 사용자 ID로 사용자를 찾은 후에 새로운 AccessToken을 생성합니다.
 */
@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if (!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }
        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2)); // todo ofHours()

    }


}
