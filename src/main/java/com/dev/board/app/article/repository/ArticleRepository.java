package com.dev.board.app.article.repository;

import com.dev.board.app.article.dao.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
