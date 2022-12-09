package com.dev.board.app.hospital.service;

import com.dev.board.app.hospital.data.dto.HospitalResponse;
import com.dev.board.app.hospital.data.dto.HospitalTotalCountResponse;
import com.dev.board.app.hospital.data.entity.Hospital;
import com.dev.board.app.hospital.repository.HospitalRepository;
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
