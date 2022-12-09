package com.dev.board.app.article.controller;

import com.dev.board.app.article.data.dto.ArticleCommentRequest;
import com.dev.board.app.article.data.entity.Article;
import com.dev.board.app.article.data.entity.ArticleComment;
import com.dev.board.app.article.repository.ArticleCommentRepository;
import com.dev.board.app.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleCommentController {

    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;

    @PostMapping("/{articleId}/comments")
    public String addComment(@PathVariable Long articleId, ArticleCommentRequest articleCommentRequest) {
        log.info(articleCommentRequest.toString());
        Article commentedArticle = articleRepository.findById(articleId).get();
        log.info(commentedArticle.toString());
        ArticleComment savedComment = articleCommentRepository.save(articleCommentRequest.toEntity(commentedArticle));
        log.info(savedComment.toString());
        return String.format("redirect:/articles/%d", articleId);
    }

    @GetMapping("/{articleId}/comments/delete/{commentId}")
    public String deleteComment(@PathVariable Long articleId, @PathVariable Long commentId) {
        log.info("articleId: {} commentId: {}", commentId.toString(), articleId.toString());
        articleCommentRepository.deleteById(commentId);
        return String.format("redirect:/articles/%d", articleId);
    }
}
