package com.dev.board.app.article.controller;

import com.dev.board.app.article.service.ArticleService;
import com.dev.board.app.article.data.dto.ArticleAddRequest;
import com.dev.board.app.article.data.dto.ArticleAddResponse;
import com.dev.board.app.article.data.dto.ArticleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ArticleRestController {

    private final ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long id) {
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
