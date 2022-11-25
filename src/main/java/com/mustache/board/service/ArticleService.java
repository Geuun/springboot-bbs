package com.mustache.board.service;

import com.mustache.board.domain.article.dto.ArticleAddRequest;
import com.mustache.board.domain.article.dto.ArticleAddResponse;
import com.mustache.board.domain.article.dto.ArticleResponse;
import com.mustache.board.domain.article.entity.Article;
import com.mustache.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleResponse getArticleResponse(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 게시물을 찾을 수 없습니다."));
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
