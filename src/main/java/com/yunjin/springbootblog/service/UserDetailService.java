package com.yunjin.springbootblog.service;

import com.yunjin.springbootblog.domain.User;
import com.yunjin.springbootblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * 스프링 시큐리티에서 사용자 정보를 가져오는 UserDetailsService 인터페이스 구현
 */
@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 사용자 이름(이메일) 기준으로 사용자를 찾습니다.
     * @param email 사용자 식별 가능한 이메일
     * @return User 엔티티 객체
     */
    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email));
    }
}
