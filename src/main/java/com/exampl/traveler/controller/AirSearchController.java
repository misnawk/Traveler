package com.exampl.traveler.controller;

import com.exampl.traveler.service.AirService;
import com.exampl.traveler.vo.AirVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AirSearchController {

    private static final Logger logger = LoggerFactory.getLogger(AirSearchController.class);

    @Autowired
    private AirService airService;

    @GetMapping("/search/air")
    public String searchAir(@RequestParam(name = "departure", required = false) String departure,
                            @RequestParam(name = "destination", required = false) String destination,
                            @RequestParam(name = "departure_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
                            @RequestParam(name = "return_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate returnDate,
                            @RequestParam(name = "passengers", defaultValue = "1") int passengers,
                            @RequestParam(name = "trip_type", defaultValue = "oneway") String tripType,
                            Model model) {

        List<AirVO> airs;
        if (departure != null && destination != null && departureDate != null) {
            airs = airService.searchAirs(departure, destination, departureDate, passengers);
        } else {
            airs = airService.getAllAirs();
        }

        model.addAttribute("airs", airs);
        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);
        model.addAttribute("departureDate", departureDate);
        model.addAttribute("returnDate", returnDate);
        model.addAttribute("passengers", passengers);
        model.addAttribute("tripType", tripType);

        return "air/airplane_search_result";
    }
}