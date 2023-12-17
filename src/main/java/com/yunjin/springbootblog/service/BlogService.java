package com.yunjin.springbootblog.service;

import com.yunjin.springbootblog.domain.Article;
import com.yunjin.springbootblog.dto.ArticleSaveRequestDto;
import com.yunjin.springbootblog.dto.ArticleUpdateRequestDto;
import com.yunjin.springbootblog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(ArticleSaveRequestDto request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    @Transactional
    public Article update(Long id, ArticleUpdateRequestDto request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getTitle(), request.getContent());
        return article;
    }
    
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

}
