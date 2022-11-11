package com.mustache.board.domain.dto;

import com.mustache.board.domain.entity.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
public class ArticleDto {
    private Long id;
    private String title;
    private String contents;

    public ArticleDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Article toEntity() {
        return new Article(title, contents);
    }

    public Article toEntity(Long id) {
        return new Article(id, title, contents);
    }
}
