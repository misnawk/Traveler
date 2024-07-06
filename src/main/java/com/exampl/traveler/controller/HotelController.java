package com.exampl.traveler.controller;

import com.exampl.traveler.service.HotelService;
import com.exampl.traveler.vo.HotelVO;
import com.exampl.traveler.vo.MemberVO;
import com.exampl.traveler.vo.UserOrderVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/hotelPayment/{hotelNO}")
    public String getHotelPayment(@PathVariable("hotelNO") String hotelNO,
                                  @RequestParam("checkin") String checkin,
                                  @RequestParam("checkout") String checkout,
                                  @RequestParam(value = "guestCount", required = false, defaultValue = "1") int guestCount,
                                  Model model) {
        HotelVO hotel = hotelService.getHotelById(hotelNO);
        model.addAttribute("hotel", hotel);
        model.addAttribute("checkin", checkin);
        model.addAttribute("checkout", checkout);
        model.addAttribute("guestCount", guestCount);
        return "hotel/hotelPayment"; // 결제 페이지 템플릿 이름
    }

    @GetMapping("/check-login")
    @ResponseBody
    public Map<String, Boolean> checkLogin(HttpSession session) {
        Boolean loggedIn = (session.getAttribute("user") != null);
        Map<String, Boolean> response = new HashMap<>();
        response.put("loggedIn", loggedIn);
        return response;
    }

    @PostMapping("/hotel/order")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody UserOrderVO orderVO, HttpSession session) {
        MemberVO user = (MemberVO) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.ok(Map.of("loggedIn", false));
        }

        try {
            orderVO.setUserId(user.getUserID());
            hotelService.insertOrder(orderVO);
            return ResponseEntity.ok(Map.of("success", true, "loggedIn", true));
        } catch (Exception e) {
            return ResponseEntity.ok(Map.of("success", false, "loggedIn", true));
        }
    }
}