package com.exampl.traveler.controller;

import com.exampl.traveler.service.HotelService;
import com.exampl.traveler.vo.RoomtypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hotel/rooms")
public class RoomController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/addRoom")
    public ResponseEntity<String> addRoom(@RequestBody RoomtypeVO roomtypeVO) {
        hotelService.addRoom(roomtypeVO);
        return ResponseEntity.ok("Room added successfully");
    }

    @GetMapping("/facilities/{hotelNO}")
    public ResponseEntity<List<Map<String, Object>>> getFacilitiesByHotel(@PathVariable("hotelNO") String hotelNO) {
        List<Map<String, Object>> facilities = hotelService.getFacilitiesByHotel(hotelNO);
        return ResponseEntity.ok(facilities);
    }

    @GetMapping("/{hotelNO}/facility/{facility}")
    public ResponseEntity<List<RoomtypeVO>> getRoomsByFacilityAndHotel(@PathVariable("hotelNO") String hotelNO, @PathVariable("facility") String facility) {
        List<RoomtypeVO> rooms = hotelService.getRoomsByFacilityAndHotel(hotelNO, facility);
        return ResponseEntity.ok(rooms);
    }
}