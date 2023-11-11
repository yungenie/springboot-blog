package com.yunjin.springbootblog.board;

import com.yunjin.springbootblog.domain.Article;
import com.yunjin.springbootblog.dto.ArticleSaveRequestDto;
import com.yunjin.springbootblog.dto.ArticleUpdateRequestDto;
import com.yunjin.springbootblog.repository.BlogRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @PostConstruct
    private void initMap(){

        for (int i = 0; i < 200; i++) {
            Board board = Board.builder()
                    .title("제목" + i)
                    .content("내용" + i)
                    .build();
            boardRepository.save(board);
        }

    }
    public Page<BoardResponse> searchPageComplex(BoardSearchCondition condition, Pageable pageable) {
        return boardRepository.searchPageComplex(condition, pageable);
    }

}
