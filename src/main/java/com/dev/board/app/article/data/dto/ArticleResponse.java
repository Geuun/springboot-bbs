package com.dev.board.app.article.data.dto;

import com.dev.board.app.article.data.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {
    private Long id;
    private String title;
    private String contents;
    private List<ArticleCommentResponse> articleComments;

    // Entity -> Dto
    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.articleComments = article.getArticleComments()
                .stream()// 스트림 생성
                .map(ArticleCommentResponse::new)//새로운 객체(ArticleCommentResponse)에 매핑
                .collect(Collectors.toList());//리스트<객체>로 출력
    }
}
