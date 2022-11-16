package com.mustache.board.controller;

import com.mustache.board.domain.article.dto.ArticleResponse;
import com.mustache.board.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockmvc;

    @MockBean
    ArticleService articleService; // => 가짜 객체로 테스트

    @Test
    @DisplayName("RESTAPI Json Response test")
    void jsonResponse() throws Exception {
        ArticleResponse articleResponse = ArticleResponse
                .builder()
                .id(Long.valueOf(29))
                .title("댓글 지우기 테스트")
                .build();

        given(articleService.getArticle(Long.valueOf(29)))
                .willReturn(articleResponse);

        Long articleId= Long.valueOf(29);

        String url = String.format("/api/v1/articles/%d", articleId);
        mockmvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("댓글 지우기 테스트"))
                .andDo(print());

        verify(articleService).getArticle(articleId);
    }
}