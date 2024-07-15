package com.exampl.traveler.controller;

import com.exampl.traveler.service.AirService;
import com.exampl.traveler.vo.AirVO;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/air")
@RequiredArgsConstructor
public class AirController {
    private static final Logger logger = LoggerFactory.getLogger(AirController.class);
    private final AirService airService;

    @GetMapping
    public String showMainPage() {
        return "air/airplane_main";
    }

    @GetMapping("/search")
    public String searchAir(@RequestParam("departure") String departure,
                            @RequestParam("destination") String destination,
                            @RequestParam("departureDate") String departureDate,
                            @RequestParam(value = "returnDate", required = false) String returnDate,
                            @RequestParam("tripType") String tripType,
                            Model model) {
        logger.info("타입:{} 출발지:{} 도착지:{} 출발날:{} 도착날:{}", tripType, departure, destination, departureDate, returnDate);

        if ("roundtrip".equalsIgnoreCase(tripType) && returnDate != null) {
            List<AirVO> departureAirs = airService.findRoundTripAirs(departure, destination, departureDate);

            model.addAttribute("airs", departureAirs);
            model.addAttribute("tripType", tripType);
            model.addAttribute("departure", departure);
            model.addAttribute("destination", destination);
            model.addAttribute("departureDate", departureDate);
            model.addAttribute("returnDate", returnDate);
            return "air/roundtrip";
        } else {
            List<AirVO> oneWayAirs = airService.findOneWayAir(departure, destination, departureDate);

            model.addAttribute("airs", oneWayAirs);
            model.addAttribute("tripType", tripType);
            model.addAttribute("departure", departure);
            model.addAttribute("destination", destination);
            model.addAttribute("departureDate", departureDate);
            return "air/oneway";
        }
    }

    @GetMapping("/roundtrip_return")
    public String showRoundtripReturnPage(@RequestParam("departure") String departure,
                                          @RequestParam("destination") String destination,
                                          @RequestParam("departureDate") String departureDate,
                                          @RequestParam("returnDate") String returnDate,
                                          @RequestParam("selectedAirNo") String selectedAirNo,
                                          Model model) {


        List<AirVO> returnAirs = airService.findRoundTripReturnAirs(destination, departure, returnDate);

        model.addAttribute("airs", returnAirs);
        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);
        model.addAttribute("departureDate", departureDate);
        model.addAttribute("returnDate", returnDate);
        model.addAttribute("selectedAirNo", selectedAirNo);
        return "air/roundtrip_return";
    }


    @GetMapping("/seatShow")
    public String seatSelection(@RequestParam("airlineNo") String airlineNo,
                                @RequestParam("tripType") String tripType,
                                Model model,
                                HttpSession session) {

        AirVO air = airService.getAirByAirlineNo(airlineNo);
        if (air == null) {
            logger.error("Airline not found: {}", airlineNo);
            // 항공편 정보를 찾을 수 없는 경우 오류 페이지로 리다이렉트
            return "redirect:/error";
        }

        String userId = (String) session.getAttribute("id");


        List<String> rows = Arrays.asList("A", "B", "C", "D");
        List<Integer> columns = Arrays.asList(1, 2, 3, 4, 5);

        model.addAttribute("rows", rows);
        model.addAttribute("columns", columns);

        List<String> availableSeats = airService.getAvailableSeats(airlineNo);


        model.addAttribute("airs", air);
        model.addAttribute("availableSeats", availableSeats);
        model.addAttribute("airlineNo", airlineNo);
        model.addAttribute("tripType", tripType);
        model.addAttribute("userId", userId);

        //유저 아이디 추가

        return "seat/seatSelection";
    }


    @PostMapping("/seat")
    @ResponseBody
    public ResponseEntity<?> reserveSeat(@RequestBody Map<String, String> requestData,
                                         HttpSession session) {
        String userId = (String) session.getAttribute("id");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("success", false, "message", "로그인이 필요합니다."));
        }

        String airlineNo = requestData.get("airlineNo");
        String seatNumber = requestData.get("seatNumber");
        String tripType = requestData.get("tripType");

        try {
            // 좌석 예약 시도
            airService.reserveSeat(airlineNo, seatNumber, userId, tripType);

            // 좌석 예약이 성공했다면, 항공편 정보를 가져옵니다.
            AirVO air = airService.getAirByAirlineNo(airlineNo);

            // 주문 및 다이어리 생성
            boolean success = airService.reserveSeatAndCreateDiary(userId, airlineNo, seatNumber, tripType,
                    air.getDepartureDateTime(),
                    air.getArrivalDateTime(),
                    air.getAirTitle());

            if (success) {
                return ResponseEntity.ok(Map.of("success", true, "message", "예약이 완료되었습니다.","redirect", "/"));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("success", false, "message", "예약 처리 중 오류가 발생했습니다."));
            }
        } catch (SeatAlreadyReservedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("success", false, "message", e.getMessage()));
        } catch (Exception e) {
            logger.error("Reservation failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "예약 처리 중 오류가 발생했습니다."));
        }
    }
}