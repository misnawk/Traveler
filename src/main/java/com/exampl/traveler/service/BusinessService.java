package com.exampl.traveler.service;

import com.exampl.traveler.mapper.BusinessMapper;
import com.exampl.traveler.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BusinessService {
    private final BusinessMapper businessMapper;

    // 수정한 기업정보 업데이트
    public boolean binProUpdate(BusinessVO vo){
        return businessMapper.binProUpdate(vo);
    }

    // 수정한 비밀번호 업데이트
    public boolean binPwUpdate(BusinessVO vo){
        return businessMapper.binPwUpdate(vo);
    }

    @Value("${imgbb.api.key}")
    private String imgbbApiKey;


    public void saveBusiness(BusinessVO businessVO) {
        businessMapper.binIdInsert(businessVO);
    }


    @Transactional
    public void saveAirline(AirVO airVO, String businessId) {
        airVO.setBinId(businessId);
        businessMapper.insertAir(airVO);
    }

    // 호텔정보 저장하기
    @Transactional
    public void createHotel(HotelVO hotelVO) {
        businessMapper.binInsertHotel(hotelVO);
    }

    @Transactional
    public void saveTicket(TicketVO ticketVO, String businessId, MultipartFile imageFile) throws Exception {
        ticketVO.setBinID(businessId);

        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = uploadImageToImgbb(imageFile);
            ticketVO.setTickImg(imageUrl);
        }

        businessMapper.insertTicket(ticketVO);
    }

    @Transactional
    public void savePackage(PackageVO packageVO, String businessId, MultipartFile imageFile) throws Exception {
        packageVO.setBinId(businessId);
        packageVO.setPackageNO("PKG" + System.currentTimeMillis());  // 간단한 패키지 번호 생성

        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = uploadImageToImgbb(imageFile);
            packageVO.setImageUrl(imageUrl);
        }

        businessMapper.insertPackage(packageVO);
    }

    public String uploadImageToImgbb(MultipartFile file) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.imgbb.com/1/upload?key=" + imgbbApiKey;

        byte[] imageBytes = file.getBytes();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("image", base64Image);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> data = (Map<String, Object>) response.getBody().get("data");
            return (String) data.get("url");
        } else {
            throw new RuntimeException("Failed to upload image to imgbb");
        }
    }
}