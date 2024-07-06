package com.exampl.traveler.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BusinessController {
    @GetMapping("/binpage/airline")
    public String airWrite(){
        return "/business/airWrite";
    }

    @GetMapping("/binpage/hotel")
    public String hotelWrite(){
        return "/business/hotelWrite";
    }

    @GetMapping("/binpage/tick")
    public String tickWrite(){
        return "/business/tickWrite";
    }

    @GetMapping("/binpage/packge")
    public String packWrite(){
        return "/business/packWrite";
    }
}
