package com.mustache.board.controller;

import com.mustache.board.domain.article.dto.ArticleAddRequest;
import com.mustache.board.domain.article.dto.ArticleAddResponse;
import com.mustache.board.domain.article.dto.ArticleResponse;
import com.mustache.board.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleRestController {

    private final ArticleService articleService;

    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> get(@PathVariable Long id) {
        ArticleResponse articleResponse = articleService.getArticleResponse(id);
        log.info(articleResponse.toString());
        return ResponseEntity
                .ok()
                .body(articleResponse);
    }

    @PostMapping
    public ResponseEntity<ArticleAddResponse> addArticle(@RequestBody ArticleAddRequest reqDto) {
        return ResponseEntity
                .ok()
                .body(articleService.addArticle(reqDto));
    }
}
