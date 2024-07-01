package com.exampl.traveler.controller;

import com.exampl.traveler.service.CityService;
import com.exampl.traveler.vo.CityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/city")
    public String showCityPage(@RequestParam("cityNO") int cityNO, Model model) {
        CityVO city = cityService.getCityByNumber(cityNO);
        model.addAttribute("city", city);
        return "/nation/city";
    }
}