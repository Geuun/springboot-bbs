package com.mustache.board.repository;

import com.mustache.board.domain.hospital.entity.Hospital;
import com.mustache.board.domain.hospital.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 병원 리뷰 리스트
    List<Review> findByHospital(Hospital hospital);
}
