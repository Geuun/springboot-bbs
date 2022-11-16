package com.mustache.board.controller;

import com.mustache.board.domain.article.dto.ArticleCommentRequest;
import com.mustache.board.domain.article.dto.ArticleCommentResponse;
import com.mustache.board.domain.article.dto.ArticleResponse;
import com.mustache.board.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("해당 id의 글이 조회가 잘 되는지")
    void findSingle() throws Exception {
        Long id = 1L;

        List<ArticleCommentResponse> comments = new ArrayList<>();
        System.out.println(comments);

        given(articleService.getArticleResponse(id)).willReturn(new ArticleResponse(1l, "첫번째 글", "내용입니다.", comments));

        mockMvc.perform(get("/api/v1/articles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("첫번째 글"))
                .andExpect(jsonPath("$.contents").value("내용입니다."))
                .andExpect(jsonPath("$.articleComments").value(comments))
                .andDo(print());

        verify(articleService).getArticleResponse(id);
    }
}