package com.exampl.traveler.vo;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
public class DiaryVO {
    private Integer diaryNO;
    private Integer orderID;
    private String userId;
    private LocalDate GoDay;
    private LocalDate BackDay;
    private Date AllDay;
    private String DiaryTitle;
    private String DiaryColor;

}
