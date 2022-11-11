package com.mustache.board.domain.dto;

import com.mustache.board.domain.entity.Article;
import com.mustache.board.domain.entity.ArticleComment;
import lombok.Getter;

@Getter

public class ArticleCommentDto {
    private Long id;

    private Article article;
    private String author;
    private String comment;

    public ArticleComment toEntity() {
        return new ArticleComment(article, author, comment);
    }
}
