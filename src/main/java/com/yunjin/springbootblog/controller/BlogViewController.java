package com.yunjin.springbootblog.controller;

import com.yunjin.springbootblog.domain.Article;
import com.yunjin.springbootblog.dto.ArticleListViewResponseDto;
import com.yunjin.springbootblog.dto.ArticleViewResponseDto;
import com.yunjin.springbootblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {

    private final BlogService blogService;

    /**
     * 블로그 글 전체보기 뷰
     * @return 블로그 글 전체 리스트를 담은 모델뷰 반환
     */
    @GetMapping("/articles")
    public String findAllArticle(Model model) {

        List<ArticleListViewResponseDto> allArticles = blogService.findAll()
                .stream()
                .map(ArticleListViewResponseDto::new)
                .toList();

        model.addAttribute("articles", allArticles);
        return "articles";
    }

    /**
     * 블로그 글 상세보기 뷰
     * @param id 글 ID
     * @return 블로그 글 ID에 해당하는 내용을 담은 모델뷰 반환
     */
    @GetMapping("/articles/{id}")
    public String findOneArticle(@PathVariable Long id, Model model) {

        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponseDto(article));

        return "article";

    }

    /**
     * 블로그 글 수정하기 뷰
     * @param id 글 ID
     * @return 블로그 글 ID에 해당하는 수정 대상 내용을 담은 모델뷰 반환
     */
    @GetMapping("/articles/edit/{id}") // todo URL 설계 확인
    public String updateOneArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponseDto(article));

        return "editArticle";
    }

    /**
     * 블로그 글 생성하기 뷰
     * @return 블로그 글 생성하기 뷰 반환
     */
    @GetMapping("/newArticle")
    public String newArticle() {
        return "newArticle";
    }
}
