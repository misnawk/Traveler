package com.exampl.traveler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PackController {
    @GetMapping("/pack")
    public String hotel(){
        return "pack/packMain";
    }
}
