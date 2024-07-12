package com.exampl.traveler.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderVO {
    private int orderId; // 자동 증가 ID
    private String userId;
    private String comNO;
    private String binCate;
    private int totalCnt;
    private Date orderDate;
    private String title; // 일반회원 관리자에서 사용
    private String time; // 일반회원 관리자에서 사용
    private Date useDate;
}