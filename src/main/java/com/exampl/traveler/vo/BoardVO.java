package com.exampl.traveler.vo;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class BoardVO{

    private Integer boardNo;

    private String userId;

    private Integer tripYn;

    private Date tripDate;


    private String tripTitle;


    private String tripText;

    private MultipartFile tripImg;  // MultipartFile 타입

    private String tripImgPath;  // 파일 경로를 저장할 필드

    private Integer tripType;

    private String packageNo;

    private Integer count;



}