package com.dev.board.app.article.service;

import com.dev.board.app.article.repository.ArticleRepository;
import com.dev.board.app.article.data.dto.ArticleAddRequest;
import com.dev.board.app.article.data.dto.ArticleAddResponse;
import com.dev.board.app.article.data.dto.ArticleResponse;
import com.dev.board.app.article.data.entity.Article;
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
