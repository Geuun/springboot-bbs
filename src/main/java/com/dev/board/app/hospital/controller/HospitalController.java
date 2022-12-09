package com.dev.board.app.hospital.controller;

import com.dev.board.app.hospital.data.entity.Hospital;
import com.dev.board.app.hospital.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hospitals")
@Slf4j
public class HospitalController {
    private final HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }


    @GetMapping("")
    public String hospitalList(Model model, Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        log.info("size: {}", hospitals.getSize());
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "hospitals/list";
    }

    @GetMapping("/search/hospitalname")
    public String searchByHospitalName(Model model, Pageable pageable, @RequestParam String searchKeyword) {
        log.info("searchKeyword: {}", searchKeyword);

        if (searchKeyword == null) {
            return "redirect:/hospitals";
        } else {
            Page<Hospital> searchedByHospitalNameList = hospitalRepository.findByHospitalNameContaining(searchKeyword, pageable);
            log.info(searchedByHospitalNameList.toString());
            log.info("size: {}", searchedByHospitalNameList.getSize());
            model.addAttribute("hospitalList", searchedByHospitalNameList);
            log.info(pageable.toString());
            model.addAttribute("searchKeyword", searchKeyword);
            model.addAttribute("previous", searchedByHospitalNameList.previousOrFirstPageable().getPageNumber());
            model.addAttribute("next", searchedByHospitalNameList.nextOrLastPageable().getPageNumber());
            return "hospitals/searchlist";
        }
    }

    @GetMapping("/search/roadnameaddress")
    public String searchByRoadNameAddress(Model model, Pageable pageable, @RequestParam String searchKeyword) {
        log.info("searchKeyword: {}", searchKeyword);

        if (searchKeyword == null) {
            return "redirect:/hospitals";
        } else {
            Page<Hospital> searchedByRoadNameAddressList = hospitalRepository.findByRoadNameAddressContaining(searchKeyword, pageable);
            log.info(searchedByRoadNameAddressList.toString());
            log.info("size: {}", searchedByRoadNameAddressList.getSize());
            model.addAttribute("hospitalList", searchedByRoadNameAddressList);
            log.info(pageable.toString());
            model.addAttribute("searchKeyword", searchKeyword);
            model.addAttribute("previous", searchedByRoadNameAddressList.previousOrFirstPageable().getPageNumber());
            model.addAttribute("next", searchedByRoadNameAddressList.nextOrLastPageable().getPageNumber());
            return "hospitals/searchlist";
        }
    }
}
