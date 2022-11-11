package com.mustache.board.controller;

import com.mustache.board.domain.dto.ArticleCommentDto;
import com.mustache.board.domain.entity.Article;
import com.mustache.board.domain.entity.ArticleComment;
import com.mustache.board.repository.ArticleCommentRepository;
import com.mustache.board.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/comments")
public class ArticleCommentController {

    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;

    public ArticleCommentController(ArticleCommentRepository articleCommentRepository, ArticleRepository articleRepository) {
        this.articleCommentRepository = articleCommentRepository;
        this.articleRepository = articleRepository;
    }
    @PostMapping("/{articleId}")
    public String addComment(@PathVariable Long articleId, ArticleCommentDto articleCommentDto, Model model) {
        
//        log.info(articleCommentDto.toString());
//        ArticleComment savedComment = articleCommentRepository.save(articleCommentDto.toEntity(id));
//        Article commentedArticle = savedComment.getArticle();
//        log.info("commented article title: {}, contents: {}", commentedArticle.getTitle(), commentedArticle.getContents());
//        model.addAttribute("comment", savedComment);
    }
}
