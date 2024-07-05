package com.exampl.traveler.vo;

import lombok.Data;

import java.util.Date;
@Data
public class DiaryVO {
    private Integer orderID;
    private String userId;
    private Date GoDay;  //시작날자
    private Date BackDay;   //종료날자
    private Date DiaryTime;
    private String DiaryTitle; //구매상품명 또는 제목
    private String DiaryText;

}
