package com.exampl.traveler.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class BoardVO{

    private Integer boardNo;

    private String userId;

    private Integer tripYn;

    private Date tripDate;

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    private String tripTitle;

    @NotBlank(message = "내용은 필수 입력 항목입니다.")
    private String tripText;

    private MultipartFile tripImg;  // MultipartFile 타입

    private String tripImgPath;  // 파일 경로를 저장할 필드

    @NotNull(message = "여행 유형은 필수 입력 항목입니다.")
    private Integer tripType;

    private String packageNo;



}