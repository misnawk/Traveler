package com.exampl.traveler.controller;

import com.exampl.traveler.service.AirService;
import com.exampl.traveler.vo.AirVO;
import com.exampl.traveler.vo.SeatVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/air")
@RequiredArgsConstructor
public class AirController {
    private static final Logger logger = LoggerFactory.getLogger(AirController.class);
    private final AirService airService;

    @GetMapping
    public String showMainPage() {
        logger.info("Showing main airplane page");
        return "air/airplane_main";
    }

    @GetMapping("/oneway")
    public String showOnewayPage() {
        logger.info("Showing oneway page");
        return "air/oneway";
    }

    @GetMapping("/roundtrip")
    public String showRoundtripPage() {
        logger.info("Showing roundtrip page");
        return "air/roundtrip";
    }

    @GetMapping("/roundtrip_return")
    public String showRoundtripReturnPage(@RequestParam("departure") String departure,
                                          @RequestParam("destination") String destination,
                                          @RequestParam("departureDate") String departureDate,
                                          @RequestParam("returnDate") String returnDate,
                                          @RequestParam("selectedAirNo") String selectedAirNo,
                                          Model model) {
        logger.info("Showing roundtrip return page for flight: {}", selectedAirNo);
        List<AirVO> returnAir = airService.findReturnAir(destination, departure, returnDate);
        model.addAttribute("airResults", returnAir);
        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);
        model.addAttribute("departureDate", departureDate);
        model.addAttribute("returnDate", returnDate);
        model.addAttribute("selectedAirNo", selectedAirNo);
        return "air/roundtrip_return";
    }

    @GetMapping("/search")
    public String searchAir(@RequestParam("departure") String departure,
                            @RequestParam("destination") String destination,
                            @RequestParam("departureDate") String departureDate,
                            @RequestParam(value = "returnDate", required = false) String returnDate,
                            @RequestParam("tripType") String tripType,
                            Model model) {
        logger.info("Searching for {} flights from {} to {}", tripType, departure, destination);
        List<AirVO> airResults;
        if ("roundtrip".equalsIgnoreCase(tripType) && returnDate != null) {
            airResults = airService.findDepartureAir(departure, destination, departureDate);
            model.addAttribute("airResults", airResults);
            model.addAttribute("tripType", tripType);
            model.addAttribute("departure", departure);
            model.addAttribute("destination", destination);
            model.addAttribute("departureDate", departureDate);
            model.addAttribute("returnDate", returnDate);
            return "air/roundtrip";
        } else {
            airResults = airService.findOneWayAir(departure, destination, departureDate);
            model.addAttribute("airResults", airResults);
            model.addAttribute("tripType", tripType);
            return "air/oneway";
        }
    }
}