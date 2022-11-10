package com.mustache.board.controller;

import com.mustache.board.domain.dto.ArticleDto;
import com.mustache.board.domain.entity.Article;
import com.mustache.board.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/list")
    public String articleListPage(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @GetMapping("")
    public String redirectListPage() {
        return "redirect:/articles/list";
    }

    @GetMapping("/{id}")
    public String selectArticlePage(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isEmpty()) {
            model.addAttribute("article", optionalArticle.get());
            return "articles/show";
        } else {
            return "errors/404error";
        }
    }

    @GetMapping("/new")
    public String createArticlePage() {
        return "articles/new";
    }

    @PostMapping("/posts/")
    public String createArticle(ArticleDto articleDto) {
        log.info(articleDto.toString());
        Article savedArticle = articleRepository.save(articleDto.toEntity());
        log.info("generatedId: {}", savedArticle.getId());
        return String.format("redirect:/articles/%d", savedArticle.getId());
    }

    @PostMapping("/{id}/edit")
    public String editArticle(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(!optionalArticle.isEmpty()) {
            model.addAttribute("article", optionalArticle.get());
            return "articles/edit";
        } else {
            model.addAttribute("message", String.format("id:%d가 없습니다.", id));
            return "errors/404error";
        }
    }
    
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDto articleDto, Model model) {
        log.info("title: {} contents: {}", articleDto.getTitle(), articleDto.getContents());
        Article updatedArticle = articleRepository.save(articleDto.toEntity(id));
        model.addAttribute("article", updatedArticle);
        return String.format("redirect:/articles/%d", updatedArticle.getId());
    }
}
