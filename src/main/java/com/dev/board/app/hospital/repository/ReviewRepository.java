package com.dev.board.app.hospital.repository;

import com.dev.board.app.hospital.data.entity.Review;
import com.dev.board.app.hospital.data.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 병원 리뷰 리스트
    List<Review> findByHospital(Hospital hospital);
}
