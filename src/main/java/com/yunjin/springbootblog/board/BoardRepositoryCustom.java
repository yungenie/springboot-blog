package com.yunjin.springbootblog.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepositoryCustom{

    Page<BoardResponse> searchPageComplex(BoardSearchCondition condition, Pageable pageable);

}
