package com.dev.board.app.article.data.dto;

import com.dev.board.app.article.data.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleAddResponse {
    private Long id;
    private String title;
    private String contents;

    // Entity -> respDto
    public ArticleAddResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.contents = article.getContents();
    }
}
