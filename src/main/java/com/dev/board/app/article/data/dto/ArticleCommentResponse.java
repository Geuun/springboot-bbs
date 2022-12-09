package com.dev.board.app.article.data.dto;

import com.dev.board.app.article.data.entity.ArticleComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
