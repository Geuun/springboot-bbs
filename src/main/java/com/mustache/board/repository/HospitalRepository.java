package com.mustache.board.repository;

import com.mustache.board.domain.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    Page<Hospital> findByHospitalNameContaining(String searchKeyword, Pageable pageable);
}
