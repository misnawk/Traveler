package com.exampl.traveler.controller;

import com.exampl.traveler.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelController {


    @Autowired
    private HotelService hotelService;

    @GetMapping("/hotel")
    public String listHotels(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "hotel/hotelMain";
    }

}
