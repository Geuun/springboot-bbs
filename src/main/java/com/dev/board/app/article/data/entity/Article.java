package com.dev.board.app.article.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "article")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ArticleComment> articleComments = new ArrayList<>();


    public Article(String title, String content) {
        this.title = title;
        this.contents = content;
    }

    public Article(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

//    public static ArticleResponse of(Article article) {
//        return new ArticleResponse(article.getId(), article.getTitle()
//                , article.getContents(), article.getArticleComments());
//    }
}
