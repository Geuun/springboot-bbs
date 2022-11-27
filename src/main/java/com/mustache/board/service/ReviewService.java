package com.mustache.board.service;

import com.mustache.board.domain.hospital.dto.ReviewAddRequest;
import com.mustache.board.domain.hospital.dto.ReviewAddResponse;
import com.mustache.board.domain.hospital.dto.ReviewResponse;
import com.mustache.board.domain.hospital.entity.Hospital;
import com.mustache.board.domain.hospital.entity.Review;
import com.mustache.board.repository.HospitalRepository;
import com.mustache.board.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;

    public ReviewAddResponse addReview(ReviewAddRequest reviewAddRequest) {
        Hospital hospital = hospitalRepository.findById(reviewAddRequest.getHospitalId()).orElseThrow(() -> new RuntimeException("해당 병원을 찾을 수 없습니다."));
        Review review = Review.builder()
                .title(reviewAddRequest.getReviewTitle())
                .userName(reviewAddRequest.getReviewAuthor())
                .content(reviewAddRequest.getReviewContent())
                .hospital(hospital)
                .build();
        Review savedReview = reviewRepository.save(review);
        return new ReviewAddResponse(savedReview.getTitle(), savedReview.getContent(), savedReview.getUserName(), "리뷰 등록에 성공하였습니다.");
    }

    public List<ReviewResponse> getReviewByHospitalId(Integer hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow(() -> new RuntimeException("해당 병원을 찾을 수 없습니다."));
        List<Review> reviews = reviewRepository.findByHospital(hospital);
        List<ReviewResponse> reviewResponse = reviews.stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());
        return reviewResponse;
    }
}
