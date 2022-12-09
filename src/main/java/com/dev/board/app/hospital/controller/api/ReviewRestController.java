package com.dev.board.app.hospital.controller.api;

import com.dev.board.app.hospital.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewRestController {

    private final ReviewService reviewService;
}
