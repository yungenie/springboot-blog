package com.yunjin.springbootblog.dto;

import com.yunjin.springbootblog.domain.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleViewResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public ArticleViewResponseDto(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}
