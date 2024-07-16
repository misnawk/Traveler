package com.exampl.traveler.controller;

import com.exampl.traveler.service.BusinessService;
import com.exampl.traveler.service.HotelService;
import com.exampl.traveler.service.LoginService;
import com.exampl.traveler.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;
    private final HotelService hotelService;
    private final LoginService loginService;

    // 기업정보 수정 페이지
    @GetMapping("binpage/editor/{id}")
    public String proEditor(@PathVariable("id") String id, HttpSession httpSession, Model model){
        String user = (String) httpSession.getAttribute("binID");

        if(ObjectUtils.isEmpty(user) || !user.equals(id)){
            return "/login/binLogin";
        } else {
            BusinessVO vo = loginService.binSelectOne(id);
            if(vo.getBinCate().equals("1")){
                vo.setBinCate("항공");
            } else if(vo.getBinCate().equals("2")){
                vo.setBinCate("숙박");
            } else if(vo.getBinCate().equals("3")){
                vo.setBinCate("티켓");
            } else if(vo.getBinCate().equals("4")){
                vo.setBinCate("패키지");
            }
            model.addAttribute("vo", vo);

            return "/business/binEditor";
        }
    }

    // 수정한 기업정보 업데이트
    @PostMapping("binpage/editor/update")
    public String proUpdate(BusinessVO vo){
        businessService.binProUpdate(vo);
        return "redirect:/binpage/"+vo.getBinID();

    }

    // 비밀번호 수정 페이지
    @GetMapping("binpage/pw/{id}")
    public String PwEditor(HttpSession httpSession, @PathVariable("id") String id){
        String user = (String) httpSession.getAttribute("binID");

        if(ObjectUtils.isEmpty(user) || !user.equals(id)){
            return "/login/binLogin";
        } else {
            return "/business/binEditorPW";
        }
    }

    // 현재 사용중인 비밀번호 확인
    @PostMapping("binpage/pw/check")
    public ResponseEntity<Boolean> binPwCheck(@RequestParam("id") String id,
                                           @RequestParam("pw") String pw,
                                           BusinessVO vo){

        boolean result = false;
        vo.setBinID(id);
        vo.setBinPW(pw);

        if (loginService.binLoginCheck(vo)) {
            result = true;
        } else {
            result = false;
        }

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    // 수정한 비밀번호 업데이트
    @PostMapping("binpage/pw/update")
    public String binPwUpdate(@RequestParam("newPW") String pw ,
                          @RequestParam("id") String id,
                          BusinessVO vo){
        vo.setBinID(id);
        vo.setBinPW(pw);

        businessService.binPwUpdate(vo);
        return "redirect:/binpage/" + id;

    }

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

    // 비즈니스 아이디로 호텔 등록하기
    @PostMapping("/business/saveHotel")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> saveHotel(@RequestBody Map<String, Object> hotelData) {
        Map<String, Object> response = new HashMap<>();
        try {
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

            businessService.createHotel(hotelVO);

            response.put("status", "success");
            response.put("hotelNO", hotelVO.getHotelNO()); // 생성된 호텔 번호 반환
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "데이터 저장 오류: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    private int parseInteger(Object value) {
        if (value == null || value.toString().isEmpty()) {
            return 0; // 기본값 설정
        }
        return Integer.parseInt(value.toString());
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

    @GetMapping("/binpage/tick")
    public String tickWrite() {
        return "/business/tickWrite";
    }

    @GetMapping("/binpage/packge")
    public String packWrite() {
        return "/business/packWrite";
    }
}