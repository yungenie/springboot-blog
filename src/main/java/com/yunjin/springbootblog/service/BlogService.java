package com.yunjin.springbootblog.service;

import com.yunjin.springbootblog.domain.Article;
import com.yunjin.springbootblog.dto.ArticleSaveRequestDto;
import com.yunjin.springbootblog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    /**
     * 블로그 글 저장
     * @param request 블로그 요청 객체
     * @return 저장된 블로그 글 엔티티
     */
    public Article save(ArticleSaveRequestDto request) {
        return blogRepository.save(request.toEntity()); //todo 왜 id는 반환하지 않는지?
    }

    /**
     * 블로그 전체 목록 조회
     * @return 저장된 블로그 전체 목록
     */
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

}
