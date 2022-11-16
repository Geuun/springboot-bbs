package com.mustache.board.service;

import com.mustache.board.domain.article.dto.ArticleAddRequest;
import com.mustache.board.domain.article.dto.ArticleAddResponse;
import com.mustache.board.domain.article.dto.ArticleResponse;
import com.mustache.board.domain.article.entity.Article;
import com.mustache.board.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleResponse getArticleResponse(Long id) {
        Optional<Article> optArticle = articleRepository.findById(id);
        Article article = optArticle.get();
        ArticleResponse articleResponse = new ArticleResponse(article);
        return  articleResponse;
    }

    public ArticleAddResponse addArticle(ArticleAddRequest reqDto) {
        Article article = reqDto.toEntity();
        Article savedArticle = articleRepository.save(article);
        return new ArticleAddResponse(savedArticle.getId(), savedArticle.getTitle(),
                savedArticle.getContents());
    }
}
