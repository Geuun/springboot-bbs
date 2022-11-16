package com.mustache.board.service;

import com.mustache.board.domain.dto.ArticleResponse;
import com.mustache.board.domain.entity.Article;
import com.mustache.board.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleResponse getArticle(Long id) {
        Optional<Article> optArticle = articleRepository.findById(id);
        Article article = optArticle.get();
        ArticleResponse articleResponse = Article.of(article);

        return  articleResponse;
    }
}
