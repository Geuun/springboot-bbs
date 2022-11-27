package com.mustache.board.controller.hospital;

import com.mustache.board.domain.hospital.dto.*;
import com.mustache.board.service.HospitalService;
import com.mustache.board.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {

    private final HospitalService hospitalService; // HospitalRestController가 HosptialService를 의존하게 변경
    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> getHospital(@PathVariable Integer id) { // ResponseEntity : Dto 타입
        HospitalResponse hospitalResponse = hospitalService.getHospital(id); // DTO
        return ResponseEntity
                .ok()
                .body(hospitalResponse); // Return은 DTO로
    }

    @GetMapping("/totalcounts")
    public ResponseEntity<HospitalTotalCountResponse> getHospitalTotalCount() {
        return ResponseEntity
                .ok()
                .body(hospitalService.getHospitalTotalCount());
    }

    @PostMapping("/{id}/reviews") // {id} 값의 병원에 댓글 추가
    public ResponseEntity<ReviewAddResponse> addReview(@RequestBody ReviewAddRequest reviewAddRequest) {
        return  ResponseEntity
                .ok()
                .body(reviewService.addReview(reviewAddRequest));
    }

    @GetMapping("/{id}/reviews") //id값의 병원 리뷰만 확인
    public ResponseEntity<List<ReviewResponse>> getReviewHospitalId(@PathVariable Integer id) {
        return ResponseEntity
                .ok()
                .body(reviewService.getReviewByHospitalId(id));
    }
}
