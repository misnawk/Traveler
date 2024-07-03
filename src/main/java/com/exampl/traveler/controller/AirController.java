package com.exampl.traveler.controller;

import com.exampl.traveler.dto.SearchCriteria;
import com.exampl.traveler.service.AirService;
import com.exampl.traveler.vo.AirVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/air")
public class AirController {

    @GetMapping
    public String showMainPage() {
        return "air/airplane_main";
    }

    private final AirService airService;

    @Autowired
    public AirController(AirService airService) {
        this.airService = airService;
    }

    @GetMapping("/round")
    public List<AirVO> findAirlineRound(
            @RequestParam("departure") String departure,
            @RequestParam("destination") String destination,
            @RequestParam("departureDate") String departureDate,
            @RequestParam("returnDate") String returnDate) {
        return airService.findAirlineRound(departure, destination, departureDate, returnDate);
    }

    @GetMapping("/oneway")
    public List<AirVO> findAirlineOne(
            @RequestParam String departure,
            @RequestParam String destination,
            @RequestParam String departureDate) {
        return airService.findAirlineOne(departure, destination, departureDate);
    }

    @GetMapping("/{airNO}")
    public AirVO findAirlineById(@PathVariable String airNO) {
        return airService.findAirlineById(airNO);
    }
}









