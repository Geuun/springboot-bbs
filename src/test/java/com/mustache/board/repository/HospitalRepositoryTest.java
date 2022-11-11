package com.mustache.board.repository;

import com.mustache.board.domain.entity.Hospital;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}