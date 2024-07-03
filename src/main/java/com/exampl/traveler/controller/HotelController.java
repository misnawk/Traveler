package com.exampl.traveler.controller;

import com.exampl.traveler.service.HotelService;
import com.exampl.traveler.vo.HotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/hotel")
    public String listHotels(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "hotel/hotelMain";
    }

    @GetMapping("/hotel/place/{place}")
    public String getHotelsByPlace(@PathVariable("place") String place, Model model) {
        List<HotelVO> hotels = hotelService.getHotelsByPlace(place);
        model.addAttribute("hotels", hotels);
        return "hotel/hotelPlace";
    }

    @GetMapping("/hotel/hotelDetail/{hotelNO}")
    public String getHotelDetail(@PathVariable("hotelNO") String hotelNO, Model model) {
        HotelVO hotel = hotelService.getHotelById(hotelNO);
        model.addAttribute("hotel", hotel);
        return "hotel/hotelDetail";
    }

    @GetMapping("/hotel/payment")
    public String showPaymentPage() {
        return "hotel/hotelPayment";
    }
}
