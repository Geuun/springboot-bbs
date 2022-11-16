package com.mustache.board.domain.article.dto;

import com.mustache.board.domain.article.entity.Article;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ArticleDto {
    private Long id;
    private String title;
    private String contents;

    // Entity -> Dto
    public ArticleDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    // Dto() -> Entity
    public Article toEntity() {
        return new Article(title, contents);
    }

    // Dto(id) -> Entity
    public Article toEntity(Long id) {
        return new Article(id, title, contents);
    }
}
