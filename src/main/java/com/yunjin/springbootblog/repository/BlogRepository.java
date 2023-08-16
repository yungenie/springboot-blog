package com.yunjin.springbootblog.repository;

import com.yunjin.springbootblog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> { // 엔티티와 PK 타입 인수로 넣어줍니다.
}
// todo 필드명 토대로 메서드를 생성해주는 규칙 알아보기