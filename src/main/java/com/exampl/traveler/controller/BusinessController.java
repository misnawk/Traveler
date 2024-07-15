package com.exampl.traveler.controller;

import com.exampl.traveler.service.BusinessService;
import com.exampl.traveler.service.HotelService;
import com.exampl.traveler.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;
    private final HotelService hotelService;







    @PostMapping("/saveAirline")
    public ResponseEntity<?> saveAirline(@RequestBody Map<String, String> AirlineData,HttpSession session) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

            AirVO airVO = new AirVO();
            airVO.setAirTitle(AirlineData.get("airTitle"));
            airVO.setDepartureAirport(AirlineData.get("departureAirport"));
            airVO.setArrivalAirport(AirlineData.get("arrivalAirport"));
            airVO.setAirCompany(AirlineData.get("airCompany"));
            airVO.setDepartureDateTime(dateFormat.parse(AirlineData.get("departureDateTime")));
            airVO.setArrivalDateTime(dateFormat.parse(AirlineData.get("arrivalDateTime")));
            airVO.setAirPrice(Integer.parseInt(AirlineData.get("airPrice")));
            airVO.setCityNO(Integer.parseInt(AirlineData.get("cityNO")));

            String businessId = (String) session.getAttribute("binID");
            businessService.saveAirline(airVO, businessId);
            return ResponseEntity.ok().body(Collections.singletonMap("success", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", e.getMessage()));
        }
    }


    @PostMapping(value = "/saveTicket", consumes = "multipart/form-data")
    public ResponseEntity<?> saveTicket(@RequestPart("ticketData") String ticketDataJson,
                                        @RequestPart(value = "tickImg", required = false) MultipartFile tickImg,
                                        HttpSession session) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> ticketData = objectMapper.readValue(ticketDataJson, Map.class);

            TicketVO ticketVO = new TicketVO();
            ticketVO.setTickTitle(ticketData.get("tickTitle"));
            ticketVO.setTickText(ticketData.get("tickText"));
            ticketVO.setTickOp(ticketData.get("tickOp"));
            ticketVO.setTickPrice(Integer.parseInt(ticketData.get("tickPrice")));
            ticketVO.setTickDate(dateFormat.parse(ticketData.get("tickDate")));
            ticketVO.setTickPlace(ticketData.get("tickPlace"));
            ticketVO.setCategory(ticketData.get("category"));

            String businessId = (String) session.getAttribute("binID");

            // 이미지 처리
            if (tickImg != null && !tickImg.isEmpty()) {
                String imageUrl = businessService.uploadImageToImgbb(tickImg);
                ticketVO.setTickImg(imageUrl);
            }

            businessService.saveTicket(ticketVO, businessId, tickImg);
            return ResponseEntity.ok().body(Collections.singletonMap("success", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @PostMapping(value = "/savePackage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> savePackage(@RequestPart("packageData") String packageDataJson,
                                         @RequestPart(value = "imageFile", required = false) MultipartFile imageFile,
                                         HttpSession session) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> packageData = objectMapper.readValue(packageDataJson, Map.class);

            PackageVO packageVO = new PackageVO();
            packageVO.setPackageTitle(packageData.get("packageTitle"));
            packageVO.setPackageText(packageData.get("packageText"));
            packageVO.setPackageMax(Integer.parseInt(packageData.get("packageMax")));
            packageVO.setPackagePrice(Integer.parseInt(packageData.get("packagePrice")));
            // peopleCnt 제거 (HTML에 없음)
            packageVO.setStartDateTime(dateFormat.parse(packageData.get("startDateTime")));
            packageVO.setEndDateTime(dateFormat.parse(packageData.get("endDateTime")));
            packageVO.setPackageCountry(packageData.get("packageCountry"));

            String businessId = (String) session.getAttribute("binID");

            // 이미지 처리
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageUrl = businessService.uploadImageToImgbb(imageFile);
                packageVO.setImageUrl(imageUrl);
            }

            businessService.savePackage(packageVO, businessId,imageFile);
            return ResponseEntity.ok().body(Collections.singletonMap("success", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", e.getMessage()));
        }
    }













    @GetMapping("/binpage/airline")
    public String airWrite() {
        return "/business/airWrite";
    }

    @GetMapping("/binpage/hotel")
    public String hotelWrite() {
        return "/business/hotelWrite";
    }


    @GetMapping("/binpage/package")
    public String packageWrite() {
        return "/business/packWrite";
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
            hotelVO.setBinCate((String) hotelData.get("binCate"));

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