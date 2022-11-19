package com.mustache.board.repository;

import com.mustache.board.domain.hospital.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    // JPA 기능 활용
    // 이름(HospitalName)으로 검색
    Page<Hospital> findByHospitalNameContaining(String searchKeyword, Pageable pageable);

    // 도로명(RoadNameAddress) 특정 키워드로 검색
    Page<Hospital> findByRoadNameAddressContaining(String searchKeyword, Pageable pageable);

    // Test 업태구분명(businessType)으로 검색
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypesName);

    // Test 병상수가 특정 수 조건 사이 검색
    List<Hospital> findByTotalNumberOfBedsBetween(Integer row, Integer high);

    // Test 주소(roadNameAddress) + 업태구분명(businessType)
    List<Hospital> findByRoadNameAddressContainingAndBusinessTypeNameContaining(String roadNameAddress, String businessTypesName);
}
