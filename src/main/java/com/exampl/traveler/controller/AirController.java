package com.exampl.traveler.controller;

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
    private final AirService airService;

    @Autowired
    public AirController(AirService airService) {
        this.airService = airService;
    }

    @GetMapping("")
    public String airMainPage() {
        return "air/airplane_main";
    }

    @GetMapping("/outbound")
    @ResponseBody
    public List<AirVO> findOutboundAirs(
            @RequestParam("departure") String departure,
            @RequestParam("destination") String destination,
            @RequestParam("departureDate") String departureDate) {
        return airService.findOutboundAirs(departure, destination, departureDate);
    }

    @GetMapping("/return")
    @ResponseBody
    public List<AirVO> findReturnAirs(
            @RequestParam("departure") String departure,
            @RequestParam("destination") String destination,
            @RequestParam("returnDate") String returnDate) {
        return airService.findReturnAirs(departure, destination, returnDate);
    }

    @GetMapping("/oneway")
    @ResponseBody
    public List<AirVO> findOneWayAirs(
            @RequestParam("departure") String departure,
            @RequestParam("destination") String destination,
            @RequestParam("departureDate") String departureDate) {
        return airService.findOneWayAirs(departure, destination, departureDate);
    }

    @GetMapping("/search")
    public String searchAirs(@RequestParam("departure") String departure,
                             @RequestParam("destination") String destination,
                             @RequestParam("departureDate") String departureDate,
                             @RequestParam(value = "returnDate", required = false) String returnDate,
                             @RequestParam("tripType") String tripType,
                             Model model) {
        if ("roundtrip".equals(tripType)) {
            List<AirVO> outbound = airService.findOutboundAirs(departure, destination, departureDate);
            model.addAttribute("outboundFlights", outbound);
            model.addAttribute("departure", departure);
            model.addAttribute("destination", destination);
            model.addAttribute("returnDate", returnDate);
            return "air/choose_outbound";
        } else {
            List<AirVO> onewayFlights = airService.findOneWayAirs(departure, destination, departureDate);
            model.addAttribute("onewayFlights", onewayFlights);
            return "air/oneway_results";
        }
    }

    @GetMapping("/select")
    public String selectAirline(@RequestParam("airlineID") String airlineID,
                                @RequestParam("departure") String departure,
                                @RequestParam("destination") String destination,
                                @RequestParam("returnDate") String returnDate,
                                Model model) {
        // 필요한 로직을 수행합니다.
        // 모델에 필요한 데이터를 추가합니다.
        model.addAttribute("airlineID", airlineID);
        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);
        model.addAttribute("returnDate", returnDate);

        // 귀국 항공편 선택 페이지로 리디렉션합니다.
        return "redirect:/air/chooseReturn?departure=" + departure + "&destination=" + destination + "&returnDate=" + returnDate;
    }

    @GetMapping("/chooseReturn")
    public String chooseReturnAirs(@RequestParam("departure") String departure,
                                   @RequestParam("destination") String destination,
                                   @RequestParam("returnDate") String returnDate,
                                   Model model) {
        List<AirVO> returnFlights = airService.findReturnAirs(departure, destination, returnDate);
        model.addAttribute("returnFlights", returnFlights);
        return "air/choose_return";
    }
}
