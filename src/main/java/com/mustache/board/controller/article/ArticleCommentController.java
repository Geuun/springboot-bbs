package com.mustache.board.controller.article;

import com.mustache.board.domain.article.dto.ArticleCommentRequest;
import com.mustache.board.domain.article.entity.Article;
import com.mustache.board.domain.article.entity.ArticleComment;
import com.mustache.board.repository.ArticleCommentRepository;
import com.mustache.board.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/articles")
public class ArticleCommentController {

    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;

    public ArticleCommentController(ArticleCommentRepository articleCommentRepository, ArticleRepository articleRepository) {
        this.articleCommentRepository = articleCommentRepository;
        this.articleRepository = articleRepository;
    }
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