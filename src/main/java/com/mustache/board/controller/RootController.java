package com.mustache.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 메인 페이지 Controller
 */
@Controller
public class RootController {
    @GetMapping("")
    public String root() {
        return "index";
    }
}
