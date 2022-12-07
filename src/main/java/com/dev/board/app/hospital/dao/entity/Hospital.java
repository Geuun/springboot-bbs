package com.dev.board.app.hospital.dao.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    private List<Review> reives;
}
