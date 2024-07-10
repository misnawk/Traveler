package com.exampl.traveler.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserOrderVO {
    private int orderId; // 자동 증가 ID
    private String userId;
    private String comNO;
    private String bincate;
    private int totalcnt;
    private Date orderdate;
    private String diaryTitle; // 패키지 제목
    private String binID; // 호텔 예약을 의미하는 고정 값

    // 기본 생성자
    public UserOrderVO() {
    }

    // 매개변수 생성자
    public UserOrderVO(String userId, String comNO, String bincate, int totalcnt, Date orderdate, String diaryTitle, String binID) {
        this.userId = userId;
        this.comNO = comNO;
        this.bincate = bincate;
        this.totalcnt = totalcnt;
        this.orderdate = orderdate;
        this.diaryTitle = diaryTitle;
        this.binID = binID;
    }

    @Override
    public String toString() {
        return "UserOrderVO{" +
                "orderId=" + orderId +
                ", userId='" + userId + '\'' +
                ", comNO='" + comNO + '\'' +
                ", bincate='" + bincate + '\'' +
                ", totalcnt=" + totalcnt +
                ", orderdate=" + orderdate +
                ", diaryTitle='" + diaryTitle + '\'' +
                ", binID='" + binID + '\'' +
                '}';
    }
}
