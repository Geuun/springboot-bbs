package com.mustache.board.repository;

import com.mustache.board.domain.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    // JPQL 기능 활용
    // 이름(HospitalName)으로 검색
    Page<Hospital> findByHospitalNameContaining(String searchKeyword, Pageable pageable);

    // 업태구분명(businessType)으로 검색
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);
}
