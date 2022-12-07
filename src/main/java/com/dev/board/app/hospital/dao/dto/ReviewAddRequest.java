package com.dev.board.app.hospital.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewAddRequest {
    private Integer hospitalId; // 리뷰등록 할 병원 id
    private String reviewTitle; // 리뷰 제목
    private String reviewAuthor; // 리뷰 작성자
    private String reviewContent; // 리뷰 콘텐츠
}
