package com.dev.board.app.hospital.dao.dto;

import com.dev.board.app.hospital.dao.entity.Hospital;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HospitalResponse {
    private Integer hospitalId;
    private String roadNameAddress;
    private String hospitalName;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;
    private String phoneNum;

    // Service Layer
    private String businessStatusName;

    // Entity -> DTO
    public static HospitalResponse of(Hospital hospital) {
        return HospitalResponse.builder()
                .hospitalId(hospital.getId())
                .roadNameAddress(hospital.getRoadNameAddress())
                .hospitalName(hospital.getHospitalName())
                .patientRoomCount(hospital.getPatientRoomCount())
                .totalNumberOfBeds(hospital.getTotalNumberOfBeds())
                .businessTypeName(hospital.getBusinessTypeName())
                .totalAreaSize(hospital.getTotalAreaSize())
                .phoneNum(hospital.getPhoneNum())
                .build();
    }

    public void setBusinessStatusName(String businessStatusName) {
        this.businessStatusName = businessStatusName;
    }
}
