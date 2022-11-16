package com.mustache.board.domain.hospital.entity;

import com.mustache.board.domain.hospital.dto.HospitalResponse;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nation_wide_hospitals")
@Getter
public class Hospital {
    @Id
    private Integer id;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "road_name_address")
    private String roadNameAddress;
    private String businessTypeName;
    @Column(name = "phone")
    private String phoneNum;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private Integer businessStatusCode;
    private Float totalAreaSize;

    // HospitalEntity를 HospitalResponse Dto로 만들어주는 부분 Response에 생성자 필요
    // Static 으로 선언한것은 Hospital.of(hospital.get())으로 가독성이 좋다.
    // Static이 아닐경우 hospital.get().of(hospital.get())
    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(),
                hospital.getRoadNameAddress(), hospital.getHospitalName(),
                hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(), hospital.getBusinessTypeName(),
                hospital.getTotalAreaSize(),hospital.getPhoneNum());
    }
}
