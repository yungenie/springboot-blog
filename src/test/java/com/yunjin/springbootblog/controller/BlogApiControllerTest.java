package com.yunjin.springbootblog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunjin.springbootblog.domain.Article;
import com.yunjin.springbootblog.dto.ArticleSaveRequestDto;
import com.yunjin.springbootblog.dto.ArticleUpdateRequestDto;
import com.yunjin.springbootblog.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
        blogRepository.deleteAll();
    }

    @Test
    public void Articles_블로그_글_등록() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "제목";
        final String content = "내용";
        final ArticleSaveRequestDto requestDto = ArticleSaveRequestDto.builder()
                .title(title)
                .content(content)
                .build();

        // when
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(requestDto)))
                        .andDo(print())
                        .andExpect(status().isCreated());

        // then
        List<Article> all = blogRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Articles_블로그_글_등록_테스트코드리팩토리() throws Exception {
        final String requestBody = objectMapper.writeValueAsString(new ArticleSaveRequestDto("제목a", "내용a"));

        mockMvc.perform(post("/api/articles")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(requestBody));
    }

    @Test
    public void Articles_블로그_글_전체조회() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "제목";
        final String content = "내용";

        blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // then
        resultActions
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));

    }

    @Test
    public void Articles_블로그_글_단일조회() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "제목";
        final String content = "내용";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()))
                .andDo(print())
                .andExpect(status().isOk());

        // then
        resultActions
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));

    }

    @Test
    public void Articles_블로그_글_수정() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "제목";
        final String content = "내용";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        final String updateTitle = "제목 수정";
        final String updateContent = "내용 수정";

        // when
        mockMvc.perform(put(url, savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(new ArticleUpdateRequestDto(updateTitle, updateContent))))
                .andDo(print())
                .andExpect(status().isOk());

        // then
        Article article = blogRepository.findById(savedArticle.getId()).orElseThrow(() -> new IllegalArgumentException("not found : " + savedArticle.getId()));
        assertThat(article.getTitle()).isEqualTo(updateTitle);
        assertThat(article.getContent()).isEqualTo(updateContent);
    }

    @Test
    public void Articles_블로그_글_삭제() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "제목";
        final String content = "내용";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        // when
        mockMvc.perform(delete(url, savedArticle.getId()))
                .andDo(print())
                .andExpect(status().isOk());

        // then
        List<Article> articles = blogRepository.findAll();
        assertThat(articles).isEmpty();
    }
}