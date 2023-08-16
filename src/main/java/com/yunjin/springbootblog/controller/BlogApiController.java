package com.yunjin.springbootblog.controller;

import com.yunjin.springbootblog.domain.Article;
import com.yunjin.springbootblog.dto.ArticleSaveRequestDto;
import com.yunjin.springbootblog.dto.ArticleResponseDto;
import com.yunjin.springbootblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody ArticleSaveRequestDto request) {
        Article saveArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saveArticle);
        // todo 상세화면으로 리다이렉트
        // todo HttpStatus.CREATED로 한 이유?
        //return "redirect:/api/articles/{articleId}";
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponseDto>> findAllArticles() {
        List<ArticleResponseDto> articles = blogService.findAll()
                .stream()
                .map(ArticleResponseDto::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles); // todo ResponseEntity는 객체, 문자열, 등 응답을 어떤 형태로 주는 지?
    }
}
