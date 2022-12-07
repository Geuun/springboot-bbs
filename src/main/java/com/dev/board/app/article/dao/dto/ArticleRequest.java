package com.dev.board.app.article.dao.dto;

import com.dev.board.app.article.dao.entity.Article;
import lombok.*;

@Getter
@ToString
public class ArticleRequest {
    private Long id;
    private String title;
    private String contents;

    // Entity -> Dto
//    public ArticleRequest(String title, String contents) {
//        this.title = title;
//        this.contents = contents;
//    }

    // Dto() -> Entity
    public Article toEntity() {
        return new Article(title, contents);
    }

    // Dto(id) -> Entity
    public Article toEntity(Long id) {
        return new Article(id, title, contents);
    }
}
