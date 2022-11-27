package com.mustache.board.domain.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewAddResponse {
    private String reviewTitle; // 리뷰 제목
    private String reviewAuthor; // 리뷰 작성자
    private String reviewContent; // 리뷰 콘텐츠
    private String message; // 응답 시스템 메세지
}
