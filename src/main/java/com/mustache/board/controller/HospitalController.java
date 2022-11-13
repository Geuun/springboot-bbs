package com.mustache.board.controller;

import com.mustache.board.domain.entity.Hospital;
import com.mustache.board.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/search")
    public String searchHospital(Model model, Pageable pageable, String searchKeyword) {
        log.info("searchKeyword: {}", searchKeyword);
        Page<Hospital> searchedHospitals = null;
        if (searchKeyword == null) {
            return "redirect:/hospitals";
        } else {
            searchedHospitals = hospitalRepository.findByHospitalNameContaining(searchKeyword, pageable);
            log.info(searchedHospitals.toString());
            log.info("size: {}", searchedHospitals.getSize());
            model.addAttribute("hospitals", searchedHospitals);
            log.info(pageable.toString());
            model.addAttribute("searchKeyword", searchKeyword);
            model.addAttribute("previous", searchedHospitals.previousOrFirstPageable().getPageNumber());
            model.addAttribute("next", searchedHospitals.nextOrLastPageable().getPageNumber());
            return "hospitals/searchlist";
        }
    }
}
