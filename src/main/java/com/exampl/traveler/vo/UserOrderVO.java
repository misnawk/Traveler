package com.exampl.traveler.vo;

import lombok.Data;
import java.util.Date;

@Data
public class UserOrderVO {
    private int orderId; // 자동 증가 ID
    private String userId;
    private String comNo;
    private String bincate;
    private int totalCnt;
    private Date orderDate;
    private String diaryTitle; // 패키지 제목
    private String binID; //
    private int totalcnt; //
    private Date orderdate; //

    // 기본 생성자
    public UserOrderVO() {
    }

    // 매개변수 생성자
    public UserOrderVO(String userId, String comNo, String bincate, int totalCnt, Date orderDate, String diaryTitle, String binID, int totalcnt, Date orderdate) {
        this.userId = userId;
        this.comNo = comNo;
        this.bincate = bincate;
        this.totalCnt = totalCnt;
        this.orderDate = orderDate;
        this.diaryTitle = diaryTitle;
        this.binID = binID;
        this.totalcnt = totalcnt;
        this.orderdate = orderdate;
    }

    @Override
    public String toString() {
        return "UserOrderVO{" +
                "orderId=" + orderId +
                ", userId='" + userId + '\'' +
                ", comNo='" + comNo + '\'' +
                ", bincate='" + bincate + '\'' +
                ", totalCnt=" + totalCnt +
                ", orderDate=" + orderDate +
                ", diaryTitle='" + diaryTitle + '\'' +
                ", binID='" + binID + '\'' +
                ", totalcnt=" + totalcnt +
                ", orderdate=" + orderdate +
                '}';
    }
}
