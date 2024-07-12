package com.exampl.traveler.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class HotelVO {
    private String hotelNO;
    private String hotelName;
    private int hotelPrice;
    private String hotelImg;
    private String hotelImg1;
    private String hotelImg2;
    private String hotelImg3;
    private String hotelImg4;
    private String hotelImg5;
    private String hotelImg6;
    private String hotelText;
    private String hotelFacility;
    private String hotelCountry;
    private String hotelTime;
    private String hotelCheck;
    private String hotelAddr;
    private String hotelDescription;
    private String hotelSights;
    private int hotelTotal;
    private String binID;
    private String binCate;
}
