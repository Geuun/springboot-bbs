package com.mustache.board.repository;

import com.mustache.board.domain.entity.Hospital;
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
}