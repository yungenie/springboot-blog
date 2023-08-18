package com.yunjin.springbootblog.repository;

import com.yunjin.springbootblog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> { // 엔티티와 PK 타입 인수로 넣어줍니다.
}
