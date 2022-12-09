package com.dev.board.app.article.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dev.board.app.article.controller.ArticleRestController;
import com.dev.board.app.article.data.dto.ArticleAddRequest;
import com.dev.board.app.article.data.dto.ArticleAddResponse;
import com.dev.board.app.article.data.dto.ArticleCommentResponse;
import com.dev.board.app.article.data.dto.ArticleResponse;
import com.dev.board.app.article.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("해당 id의 글이 조회가 잘 되는지")
    void findSingle() throws Exception {
        Long id = 1L;

        List<ArticleCommentResponse> comments = new ArrayList<>();
        System.out.println(comments);

        given(articleService.getArticleResponse(id))
                .willReturn(new ArticleResponse(1l, "첫번째 글", "내용입니다.", comments));

        mockMvc.perform(get("/api/v1/articles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("첫번째 글"))
                .andExpect(jsonPath("$.contents").exists())
                .andExpect(jsonPath("$.contents").value("내용입니다."))
                .andExpect(jsonPath("$.articleComments").exists())
                .andExpect(jsonPath("$.articleComments").value(comments))
                .andDo(print());

        verify(articleService).getArticleResponse(id);
    }

    @Test
    @DisplayName("Add ArticleRest API Test")
    void addArticle() throws Exception {
        ArticleAddRequest reqDto = new ArticleAddRequest("제목입니다.", "내용입니다.");
        ArticleAddResponse respDto = new ArticleAddResponse(1l, reqDto.getTitle(), reqDto.getContents());

        given(articleService.addArticle(any())) // any() => 가짜 객체라서 어느 객체가 들어가도 상관 없
                .willReturn(respDto);

        mockMvc.perform(post("/api/v1/ariticles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(reqDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.contents").exists())
                .andDo(print());

        verify(articleService).addArticle(reqDto);
    }
}