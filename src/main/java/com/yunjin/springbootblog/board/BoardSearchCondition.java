package com.yunjin.springbootblog.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardSearchCondition {

    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
