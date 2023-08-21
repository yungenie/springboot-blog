package com.yunjin.springbootblog.dto;

import com.yunjin.springbootblog.domain.Article;
import lombok.Getter;

@Getter
public class ArticleListViewResponseDto {

    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponseDto(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
