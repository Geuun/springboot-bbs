package com.mustache.board.controller.hospital;

import com.mustache.board.domain.hospital.dto.HospitalResponse;
import com.mustache.board.domain.hospital.dto.HospitalTotalCountResponse;
import com.mustache.board.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {

    private final HospitalService hospitalService; // HospitalRestController가 HosptialService를 의존하게 변경

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> getHospital(@PathVariable Integer id) { // ResponseEntity : Dto 타입
        HospitalResponse hospitalResponse = hospitalService.getHospital(id); // DTO
        return ResponseEntity
                .ok()
                .body(hospitalResponse); // Return은 DTO로
    }

    @GetMapping("/totalcounts")
    public ResponseEntity<HospitalTotalCountResponse> getHospitalTotalCount() {
        return ResponseEntity
                .ok()
                .body(hospitalService.getHospitalTotalCount());
    }
}
