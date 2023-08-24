package com.yunjin.springbootblog.repository;

import com.yunjin.springbootblog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { // 엔티티와 PK 타입 인수로 넣어줍니다.
    Optional<User> findByEmail(String email); // email로 사용자 정보를 가져옴
}
