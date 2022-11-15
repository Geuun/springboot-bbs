package com.mustache.board.controller;

import com.mustache.board.domain.dto.HospitalResponse;
import com.mustache.board.service.HospitalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HospitalRestController.class)
class HospitalRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean // Autowired가 아니다. HospitalService 테스트를 위해 각자 객체를 쓰겠다는 뜻이다.
    HospitalService hospitalService; // => 가짜 객체를 쓰면 DB와 상관없이 테스트가 가능하다.

    @Test
    @DisplayName("1개의 Json 형태로 Response가 잘 나오는지 Test")
    void jsonResponse() throws Exception {
        /**
         * {
         * "id": 7777,
         * "roadNameAddress": "충청남도 천안시 서북구 성거읍 성거길 89",
         * "hospitalName": "참조은한의원",
         * "patientRoomCount": 0,
         * "totalNumberOfBeds": 0,
         * "businessTypeName": "한의원",
         * "totalAreaSize": 121.65,
         * "phoneNum": "415232578",
         * "businessStatusName": "영업중"
         * }
         */
        HospitalResponse hospitalResponse = HospitalResponse
                .builder().id(7777)
                .roadNameAddress("충청남도 천안시 서북구 성거읍 성거길 89")
                .hospitalName("참조은한의원")
                .patientRoomCount(0)
                .totalNumberOfBeds(0)
                .businessTypeName("한의원")
                .totalAreaSize(121.65f)
                .phoneNum("5232578")
                .businessStatusName("영업중")
                .build();

        given(hospitalService.getHospital(7777))
                .willReturn(hospitalResponse);

        int hospitalId = 7777;

        // Autowired한 mockMvc
        String url = String.format("/api/v1/hospitals/%d", hospitalId);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hospitalName").exists())  // $는 루트 $아래에 hospitalName이 있어야 함
                .andExpect(jsonPath("$.hospitalName").value("참조은한의원"))
                .andDo(print()); // http request, response내역을 출력 해라

        verify(hospitalService).getHospital(hospitalId);// getHospital()메소드의 호출이 있었는지 확인
    }
}