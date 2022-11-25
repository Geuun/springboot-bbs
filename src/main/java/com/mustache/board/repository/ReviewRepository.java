package com.mustache.board.repository;

import com.mustache.board.domain.hospital.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
