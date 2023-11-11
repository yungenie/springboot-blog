package com.yunjin.springbootblog.board;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class BoardResponse {

    private Long boardId;
    private String boardTitle;
    private String boardContent;

    @QueryProjection
    public BoardResponse(Long boardId, String boardTitle, String boardContent) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }

}
