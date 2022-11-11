package com.mustache.board.domain.entity;

import lombok.Getter;
import org.springframework.aop.IntroductionInfo;

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

    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private Float totalAreaSize;
}
