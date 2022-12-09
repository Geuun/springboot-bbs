package com.dev.board.app.article.data.dto;

import com.dev.board.app.article.data.entity.Article;
import com.dev.board.app.article.data.entity.ArticleComment;
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
