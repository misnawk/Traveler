package com.exampl.traveler.vo;

import lombok.Data;

import java.util.Date;

@Data
public class OrdersVO {
    private int orderId; // 자동 증가 ID
    private String userId;
    private String comNo;
    private String bincate;
    private int totalCnt;
    private Date orderDate;
    private String diaryTitle; // 패키지 제목

    // 기본 생성자
    public OrdersVO() {
    }

    public OrdersVO(String userId, String comNo, String bincate, int totalCnt, Date orderDate, String diaryTitle) {
        this.userId = userId;
        this.comNo = comNo;
        this.bincate = bincate;
        this.totalCnt = totalCnt;
        this.orderDate = orderDate;
        this.diaryTitle = diaryTitle;
    }
}
