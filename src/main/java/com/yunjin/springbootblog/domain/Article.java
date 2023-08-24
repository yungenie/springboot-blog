package com.yunjin.springbootblog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@Entity
@Table(name= "article")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Class 'Article' should have [public, protected] no-arg constructor
public class Article {

    @Id // Persistent entity 'Article' should have primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임 (기본키 자동으로 1씩 증가)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition="TEXT")
    private String content;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
