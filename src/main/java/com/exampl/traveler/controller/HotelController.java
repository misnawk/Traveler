package com.exampl.traveler.controller;

import com.exampl.traveler.service.HotelService;
import com.exampl.traveler.vo.HotelVO;
import com.exampl.traveler.vo.UserOrderVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/hotel")
    public String listHotels(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "hotel/hotelMain";
    }

    @GetMapping("/hotel/country/{country}")
    public String getHotelsByPlace(@PathVariable("country") String country,
                                   @RequestParam(value = "checkin", required = false) String checkin,
                                   @RequestParam(value = "checkout", required = false) String checkout,
                                   @RequestParam(value = "guestCount", required = false) Integer guestCount,
                                   Model model) {
        List<HotelVO> hotels = hotelService.getHotelsByPlace(country);
        model.addAttribute("hotels", hotels);
        model.addAttribute("checkin", checkin);
        model.addAttribute("checkout", checkout);
        model.addAttribute("guestCount", guestCount);
        return "hotel/hotelMain";
    }

    @GetMapping("/hotelDetail/{hotelNO}")
    public String getHotelDetail(@PathVariable("hotelNO") String hotelNO,
                                 @RequestParam(value = "checkin", required = false) String checkin,
                                 @RequestParam(value = "checkout", required = false) String checkout,
                                 @RequestParam(value = "guestCount", required = false, defaultValue = "1") int guestCount,
                                 Model model) {
        HotelVO hotel = hotelService.getHotelById(hotelNO);
        model.addAttribute("hotel", hotel);
        model.addAttribute("checkin", checkin);
        model.addAttribute("checkout", checkout);
        model.addAttribute("guestCount", guestCount);
        return "hotel/hotelDetail"; // 호텔 디테일 페이지 템플릿 이름
    }

    @GetMapping("/check-login")
    @ResponseBody
    public Map<String, Boolean> checkLogin(HttpSession session) {
        Map<String, Boolean> response = new HashMap<>();
        String userId = (String) session.getAttribute("id");
        boolean loggedIn = (userId != null);
        response.put("loggedIn", loggedIn);

        // 디버깅을 위한 로그 추가
        System.out.println("Session ID: " + session.getId());
        System.out.println("User ID in session: " + userId);
        System.out.println("Is logged in: " + loggedIn);

        return response;
    }

    @GetMapping("/hotelPayment/{hotelNO}")
    public String getHotelPayment(@PathVariable("hotelNO") String hotelNO,
                                  @RequestParam("checkin") String checkin,
                                  @RequestParam("checkout") String checkout,
                                  @RequestParam(value = "guestCount", required = false, defaultValue = "1") int guestCount,
                                  @RequestParam("roomName") String roomName,
                                  @RequestParam("roomFacility") String roomFacility,
                                  @RequestParam("roomMax") int roomMax,
                                  Model model, HttpSession session) {
        String userId = (String) session.getAttribute("id");
        if (userId == null) {
            return "redirect:/login";
        }

        HotelVO hotel = hotelService.getHotelById(hotelNO);
        model.addAttribute("hotel", hotel);
        model.addAttribute("checkin", checkin);
        model.addAttribute("checkout", checkout);
        model.addAttribute("guestCount", guestCount);
        model.addAttribute("roomName", roomName);
        model.addAttribute("roomFacility", roomFacility);
        model.addAttribute("roomMax", roomMax);
        return "hotel/hotelPayment"; // 결제 페이지 템플릿 이름
    }

    @PostMapping("hotel/{hotelNO}/order")
    public ResponseEntity<?> orderHotel(@PathVariable("hotelNO") String hotelNO,
                                        @RequestParam(value = "binCate", defaultValue = "2") String binCate,
                                        @RequestParam("peopleCount") int peopleCount,
                                        @RequestParam("useDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date useDate,
                                        HttpSession session) {
        String userId = (String) session.getAttribute("id");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "로그인이 필요합니다."));
        }

        try {
            UserOrderVO order = hotelService.createOrder(userId, hotelNO, binCate, peopleCount, useDate);
            return ResponseEntity.ok(Collections.singletonMap("orderId", order.getOrderId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "주문 처리 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }

    @PostMapping("hotel/{hotelNO}/diary")
    public ResponseEntity<?> createDiary(@PathVariable("hotelNO") String hotelNO,
                                         @RequestParam("orderId") int orderId,
                                         @RequestParam("goday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date goday,
                                         @RequestParam("backday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date backday,
                                         @RequestParam("hotelName") String hotelName,
                                         @RequestParam("hotelText") String hotelText,
                                         @RequestParam("checkInTime") @DateTimeFormat(pattern = "HH:mm") Date checkInTime,
                                         HttpSession session) {
        String userId = (String) session.getAttribute("id");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "로그인이 필요합니다."));
        }
        try {
            if (checkInTime == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "체크인 시간이 제공되지 않았습니다."));
            }

            // goday와 checkInTime을 결합하여 완전한 체크인 날짜시간 생성
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(goday);
            Calendar checkInCalendar = Calendar.getInstance();
            checkInCalendar.setTime(checkInTime);
            calendar.set(Calendar.HOUR_OF_DAY, checkInCalendar.get(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, checkInCalendar.get(Calendar.MINUTE));
            Date fullCheckInTime = calendar.getTime();

            hotelService.createDiary(userId, orderId, goday, backday, hotelName);
            return ResponseEntity.ok(Collections.singletonMap("success", true));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "다이어리 생성 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }
}