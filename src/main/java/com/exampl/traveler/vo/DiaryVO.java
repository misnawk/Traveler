package com.exampl.traveler.vo;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
public class DiaryVO {
    private Integer diaryNO;
    private Integer orderID;
    private String userId;
    private Date goDay;  // LocalDate로 변경
    private Date backDay;    //종료날자
    private Date AllDay;
    private String DiaryTitle; //구매상품명 또는 제목
    private String DiaryColor;

}
