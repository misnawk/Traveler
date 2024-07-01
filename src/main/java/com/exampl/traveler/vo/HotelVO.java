package com.exampl.traveler.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class HotelVO {
    @Id
    private String hotelNO;
    private String hotelName;
    private int hotelprice;
    private String hotelImg;
    private String hotelText;
    private String hotelFacility;
    private String hotelplace;
    private String hotelTime;
    private String hotelCheck;
}
