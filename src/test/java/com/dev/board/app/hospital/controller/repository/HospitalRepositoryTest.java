package com.dev.board.app.hospital.controller.repository;

import com.dev.board.app.hospital.data.entity.Hospital;
import com.dev.board.app.hospital.repository.HospitalRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    void findById() {
        Optional<Hospital> hospital = hospitalRepository.findById(1);
        Hospital hp = hospital.get();

        assertEquals(1, hp.getId());
        assertEquals("효치과의원", hp.getHospitalName());
    }

    @Test
    @DisplayName("BusinessTypeName이 보건소 보건지소 보건진료소인 데이터 쿼리문 잘 짰는지 Test")
    void findBybusinessTypeNameIn() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");

        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(inClues);
        for (var hospital : hospitals) {
            System.out.println(hospital.getHospitalName());
        }
    }

    @Test
    @DisplayName("findByRoadNameAddressContainingAndBusinessTypeName(): 주소(roadNameAddress) + 업태구분명(businessType) 검색 Test")
    void findByRoadNameAddressContainingAndBusinessTypeName() {
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContainingAndBusinessTypeNameContaining("송파구", "보건");
        for (var hospital : hospitals) {
            System.out.println(hospital.getHospitalName());
        }
    }

    @Test
    @DisplayName("findByTotalNumberOfBedsBetween(): 병상 수가 특정 수 이상 특정 수 미만인 병원을 찾는 Test")
    void findByTotalNumberOfBedsBetween() {
        List<Hospital> hospitals = hospitalRepository.findByTotalNumberOfBedsBetween(10, 20);
        for (var hospital : hospitals) {
            System.out.println(hospital.getHospitalName());
        }
    }

    @Test
    @DisplayName(" 전체 병원 데이터 수를 가져올 수 있는지 Test")
    void getHospitalTotalCount() {
        Long getHospitalTotalCount = hospitalRepository.countBy();
        System.out.println(getHospitalTotalCount);
    }
}