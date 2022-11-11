package com.mustache.board.domain.dto;

import com.mustache.board.domain.entity.Article;
import com.mustache.board.domain.entity.ArticleComment;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ArticleCommentDto {
    private String author;
    private String comment;

    // Entity -> Dto

    public ArticleCommentDto(String author, String comment) {
        this.author = author;
        this.comment = comment;
    }

    // Dto -> Entity
    public ArticleComment toEntity(Article article) {
        return new ArticleComment(article, author, comment);
    }
}
