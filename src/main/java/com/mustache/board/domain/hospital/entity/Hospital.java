package com.mustache.board.domain.hospital.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "nation_wide_hospitals")
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
}
