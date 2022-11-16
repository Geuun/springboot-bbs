package com.mustache.board.domain.article.dto;

import com.mustache.board.domain.article.entity.Article;
import com.mustache.board.domain.article.entity.ArticleComment;
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
