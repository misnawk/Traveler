package com.exampl.traveler.vo;

import lombok.Data;

import java.util.Date;

@Data
public class boardVO {
    private Integer boardNo;
    private String userId;
    private Integer tripYn;
    private Date tripDate;
    private String tripTitle;
    private String tripText;
    private String tripImg;
    private Integer tripType;
    private String packageNo;

}
