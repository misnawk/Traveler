package com.exampl.traveler.controller;

import com.exampl.traveler.service.AirService;
import com.exampl.traveler.service.SeatService;
import com.exampl.traveler.vo.AirVO;
import com.exampl.traveler.vo.SeatVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/seat")
@RequiredArgsConstructor
public class SeatController {
    private static final Logger logger = LoggerFactory.getLogger(SeatController.class);
    private final SeatService seatService;
    private final AirService airService;

    @GetMapping("/selection")
    public String showSeatSelection(@RequestParam("airNo") String airNo,
                                    @RequestParam("tripType") String tripType,
                                    Model model,
                                    HttpSession session) {
        logger.info("Showing seat selection for airNo: {}", airNo);

        AirVO air = airService.getAirByAirNo(airNo);
        if (air == null) {
            logger.error("No air found for airNo: {}", airNo);
            return "redirect:/error";
        }

        List<SeatVO> seats = seatService.getSeatsForAir(air.getAirlineNo());

        model.addAttribute("air", air);
        model.addAttribute("seats", seats);
        model.addAttribute("tripType", tripType);
        model.addAttribute("userId", session.getAttribute("userId")); // 세션에서 userId 가져오기
        return "seat/selection";
    }

    @PostMapping("/reserve")
    @ResponseBody
    public ResponseEntity<?> reserveSeat(@RequestParam("airlineNo") String airlineNo,
                                         @RequestParam("seatNumber") String seatNumber,
                                         HttpSession session) {
        String userId = (String) session.getAttribute("userID");  // userID로 변경
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }

        try {
            boolean reserved = seatService.reserveSeat(airlineNo, seatNumber, userId);
            if (reserved) {
                return ResponseEntity.ok().body("Seat reserved successfully");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Seat already taken");
            }
        } catch (Exception e) {
            logger.error("Failed to reserve seat", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to reserve seat");
        }
    }
}