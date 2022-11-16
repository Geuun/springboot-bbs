package com.mustache.board.domain.article.dto;

import com.mustache.board.domain.article.entity.Article;
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
