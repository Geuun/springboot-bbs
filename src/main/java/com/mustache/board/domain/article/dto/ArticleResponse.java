package com.mustache.board.domain.article.dto;

import com.mustache.board.domain.article.entity.ArticleComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {
    private Long id;
    private String title;
    private String contents;
    private List<ArticleComment> articleComments;
}
