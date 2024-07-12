package com.exampl.traveler.controller;

import com.exampl.traveler.service.BusinessService;
import com.exampl.traveler.service.HotelService;
import com.exampl.traveler.vo.BusinessVO;
import com.exampl.traveler.vo.HotelVO;
import com.exampl.traveler.vo.RoomtypeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;
    private final HotelService hotelService;


    @GetMapping("/binpage/airline")
    public String airWrite() {
        return "/business/airWrite";
    }

    @GetMapping("/binpage/hotel")
    public String hotelWrite() {
        return "/business/hotelWrite";
    }

    @PostMapping("/business/saveHotelAndRoom")
    @ResponseBody
    public ResponseEntity<String> saveHotelAndRoom(@RequestBody Map<String, Object> requestData) {
        try {
            Map<String, Object> hotelData = (Map<String, Object>) requestData.get("hotel");
            Map<String, Object> roomData = (Map<String, Object>) requestData.get("room");

            HotelVO hotelVO = new HotelVO();
            hotelVO.setHotelName((String) hotelData.get("hotelName"));
            hotelVO.setHotelPrice(parseInteger(hotelData.get("hotelPrice")));
            hotelVO.setHotelImg((String) hotelData.get("hotelImg"));
            hotelVO.setHotelImg1((String) hotelData.get("hotelImg1"));
            hotelVO.setHotelImg2((String) hotelData.get("hotelImg2"));
            hotelVO.setHotelImg3((String) hotelData.get("hotelImg3"));
            hotelVO.setHotelImg4((String) hotelData.get("hotelImg4"));
            hotelVO.setHotelImg5((String) hotelData.get("hotelImg5"));
            hotelVO.setHotelImg6((String) hotelData.get("hotelImg6"));
            hotelVO.setHotelText((String) hotelData.get("hotelText"));
            hotelVO.setHotelFacility((String) hotelData.get("hotelFacility"));
            hotelVO.setHotelCountry((String) hotelData.get("hotelCountry"));
            hotelVO.setHotelTime((String) hotelData.get("hotelTime"));
            hotelVO.setHotelCheck((String) hotelData.get("hotelCheck"));
            hotelVO.setHotelAddr((String) hotelData.get("hotelAddr"));
            hotelVO.setHotelDescription((String) hotelData.get("hotelDescription"));
            hotelVO.setHotelSights((String) hotelData.get("hotelSights"));
            hotelVO.setHotelTotal(parseInteger(hotelData.get("hotelTotal")));
            hotelVO.setBinID((String) hotelData.get("binID"));
            hotelVO.setBinCate(parseInteger(hotelData.get("binCate")));

            RoomtypeVO roomVO = new RoomtypeVO();
            roomVO.setRoomName((String) roomData.get("roomName"));
            roomVO.setRoomImg((String) roomData.get("roomImg"));
            roomVO.setRoomFacility((String) roomData.get("roomFacility"));
            roomVO.setRoomMax(parseInteger(roomData.get("roomMax")));

            hotelService.createHotelAndRoom(hotelVO, roomVO);

            return ResponseEntity.ok("Data saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving data: " + e.getMessage());
        }
    }

    private int parseInteger(Object value) {
        if (value == null || value.toString().isEmpty()) {
            return 0; // 기본값 설정
        }
        return Integer.parseInt(value.toString());
    }


    @GetMapping("/binpage/tick")
    public String tickWrite() {
        return "/business/tickWrite";
    }

    @GetMapping("/binpage/packge")
    public String packWrite() {
        return "/business/packWrite";
    }
}