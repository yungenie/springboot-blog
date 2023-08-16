package com.yunjin.springbootblog.dto;

import com.yunjin.springbootblog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Getter
public class ArticleSaveRequestDto {

    private String title;
    private String content;

    @Builder
    public ArticleSaveRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        return Article.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
