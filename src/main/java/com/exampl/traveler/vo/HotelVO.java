package com.exampl.traveler.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Data
public class HotelVO {
    @Id
    private String hotelNO;         // 호텔번호
    private String hotelName;       // 호텔이름
    private int hotelPrice;         // 호텔가격
    private String hotelImg;        // 호텔이미지0 (메인)
    private String hotelImg1;       // 호텔이미지1
    private String hotelImg2;       // 호텔이미지2
    private String hotelImg3;       // 호텔이미지3
    private String hotelImg4;       // 호텔이미지4
    private String hotelImg5;       // 호텔이미지5
    private String hotelImg6;       // 호텔이미지6
    private String hotelText;       // 호텔한줄소개
    private String hotelFacility;   // 호텔시설
    private String hotelCountry;    // 호텔나라
    private String hotelTime;       // 호텔시간
    private String hotelCheck;      // 호텔주의사항
    private String hotelAddr;       // 호텔주소
    private String hotelDescription;// 호텔설명
    private String hotelSights;     // 호텔명소
    private int hotelTotal;         // 호텔객실총개수
    private int binID;              // 호텔 binID
}
