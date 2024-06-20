package com.exampl.traveler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String main(Model model){
        return "/main/main";
    }
    @GetMapping("/header")
    public String Header(Model model) {
        return "header";
    }
    @GetMapping("/footer")
    public String Footer(Model model){
        return "foter";
    }

}
