package com.yunjin.springbootblog.controller;

import com.yunjin.springbootblog.domain.Article;
import com.yunjin.springbootblog.dto.ArticleSaveRequestDto;
import com.yunjin.springbootblog.dto.ArticleResponseDto;
import com.yunjin.springbootblog.dto.ArticleUpdateRequestDto;
import com.yunjin.springbootblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogApiController {

    private final BlogService blogService;

    /**
     * 블로그 글 저장 API
     * @param request 블로그 요청 객체
     * @return 응답 상태와 Article 엔티티 반환
     */
    @PostMapping("/api/articles")
    public ResponseEntity<Article> saveArticle(@RequestBody ArticleSaveRequestDto request) {

        Article saveArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saveArticle);
    }

    /**
     * 블로그 글 전체 조회 API
     * @return 응답 상태와 Article 응답 객체의 전체 목록 반환
     */
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponseDto>> findAllArticle() {

        List<ArticleResponseDto> allArticles = blogService.findAll()
                .stream()
                .map(ArticleResponseDto::new)
                .toList();

        return ResponseEntity.ok()
                .body(allArticles);
    }

    /**
     * 블로그 글 단일 조회 API
     * @param id 글 ID
     * @return 응답 상태와 Article 응답 객체의 단일 목록 반환
     */
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponseDto> findOneArticle(@PathVariable Long id) {

        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponseDto(article));

    }


    /**
     * 블로그 글 수정 API
     * @param id 글 ID
     * @param request 수정 객체
     * @return 응답 상태와 Article 엔티티 반환
     */
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody ArticleUpdateRequestDto request) {

        Article updateArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updateArticle);
    }

    /**
     * 블로그 글 삭제 API
     * @param id 글 ID
     * @return 응답 상태 반환
     */
    @DeleteMapping("/api/articles/{id}") // todo 추후 엔티티 삭제여부 컬럼 추가 후 플래그값 update 로직으로 변경
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {

        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }
}
