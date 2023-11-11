package com.yunjin.springbootblog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/api/boards")
    public Page<BoardResponse> findAllBoards(@RequestBody BoardSearchCondition condition, Pageable pageable) {
        return boardService.searchPageComplex(condition, pageable);
    }

}
