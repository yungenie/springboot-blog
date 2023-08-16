package com.yunjin.springbootblog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Class 'Article' should have [public, protected] no-arg constructor
public class Article {

    @Id // Persistent entity 'Article' should have primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임 (기본키 자동으로 1씩 증가)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;
    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
