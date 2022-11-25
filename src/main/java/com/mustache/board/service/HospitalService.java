package com.mustache.board.service;

import com.mustache.board.domain.hospital.dto.HospitalResponse;
import com.mustache.board.domain.hospital.dto.HospitalTotalCountResponse;
import com.mustache.board.domain.hospital.entity.Hospital;
import com.mustache.board.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public HospitalResponse getHospital(Integer id) {
        Hospital hospital = hospitalRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 병원을 찾을 수 없습니다.")); // Entity
        HospitalResponse hospitalResponse = HospitalResponse.of(hospital); // DTO
        if (hospital.getBusinessStatusCode() == 13) {
            hospitalResponse.setBusinessStatusName("영업중");
        } else if (hospital.getBusinessStatusCode() == 3) {
            hospitalResponse.setBusinessStatusName("폐업");
        } else {
            hospitalResponse.setBusinessStatusName(String.valueOf(hospital.getBusinessStatusCode()));
        }
        return hospitalResponse;
    }

    public HospitalTotalCountResponse getHospitalTotalCount() {
        Long hospitalTotalcount = hospitalRepository.countBy();
        HospitalTotalCountResponse hospitalTotalCountResponse = new HospitalTotalCountResponse(hospitalTotalcount);
        return hospitalTotalCountResponse;
    }
}
