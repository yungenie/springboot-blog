package com.yunjin.springbootblog.board;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.yunjin.springbootblog.board.QBoard.board;
import static org.springframework.util.StringUtils.isEmpty;


public class BoardRepositoryImpl implements BoardRepositoryCustom {


    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    /**
     * 복잡한 페이징
     * 데이터 조회 쿼리와, 전체 카운트 쿼리를 분리
     */
    @Override
    public Page<BoardResponse> searchPageComplex(BoardSearchCondition condition, Pageable pageable) {
        List<BoardResponse> content = queryFactory
                .select(new QBoardResponse(
                        board.id,
                        board.title,
                        board.content
                        ))
                .from(board)
                .where(boardTitleEq(condition.getTitle()),
                        boardContentEq(condition.getContent()),
                        dateBetween(condition.getStartDate(), condition.getEndDate())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Board> countQuery  = queryFactory
                .select(board)
                .from(board)
                .where(boardTitleEq(condition.getTitle()),
                        boardContentEq(condition.getContent()),
                        dateBetween(condition.getStartDate(), condition.getEndDate())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }
    private BooleanExpression boardTitleEq(String boardTitle) {
        return isEmpty(boardTitle) ? null : board.title.eq(boardTitle);
    }

    private BooleanExpression boardContentEq(String boardContent) {
        return isEmpty(boardContent) ? null : board.content.eq(boardContent);
    }

    private BooleanExpression dateBetween(LocalDateTime boardStartDate, LocalDateTime boardEndDate) {
        return (boardStartDate == null) || (boardEndDate == null)? null : board.createdAt.between(boardStartDate, boardEndDate);
    }

}
