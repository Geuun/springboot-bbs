package com.dev.board.app.article.dao.dto;

import com.dev.board.app.article.dao.entity.Article;
import com.dev.board.app.article.dao.entity.ArticleComment;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ArticleCommentRequest {
    private String author;
    private String comment;

    // Dto -> Entity
    public ArticleComment toEntity(Article article) {
        return new ArticleComment(article, author, comment);
    }
}
