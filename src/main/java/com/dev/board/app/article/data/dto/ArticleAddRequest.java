package com.dev.board.app.article.data.dto;

import com.dev.board.app.article.data.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleAddRequest {
    private String title;
    private String contents;

    // reqDto -> Entity
    public Article toEntity() {
        return new Article(title, contents);
    }
}
