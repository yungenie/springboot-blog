package com.yunjin.springbootblog.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom, QuerydslPredicateExecutor<Board> {

}
