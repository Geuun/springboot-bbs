package com.dev.board.app.hospital.controller.api;

import com.dev.board.app.hospital.data.dto.ReviewAddRequest;
import com.dev.board.app.hospital.data.dto.ReviewAddResponse;
import com.dev.board.app.hospital.service.ReviewService;
import com.dev.board.app.user.data.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewRestController {

    private final ReviewService reviewService;

    @PostMapping("//")
    public Response<ReviewAddResponse> addReview(@RequestBody ReviewAddRequest reviewAddRequest, Authentication authentication) {
        log.info("Controller user: {}", authentication.getName());
        return Response.success(reviewAddRequest);
    }


}
