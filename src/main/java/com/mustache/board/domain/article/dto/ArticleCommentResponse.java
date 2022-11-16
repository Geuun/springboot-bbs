package com.mustache.board.domain.article.dto;

import com.mustache.board.domain.article.entity.ArticleComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCommentResponse {
    private String author; //댓글 작성자
    private String comment; //댓글 내용

    // Entity -> Dto
    public ArticleCommentResponse(ArticleComment articleComment) {
        this.author = articleComment.getAuthor();
        this.comment = articleComment.getComment();
    }
}
