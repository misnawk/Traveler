package com.exampl.traveler.vo;

import java.util.Date;

public class UserOrderVO {

    private String userId;
    private String comNO;
    private String binID;
    private int totalcnt;
    private Date orderdate;
    private int orderId; // 새로운 필드 추가

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
    }

    public int getTotalcnt() {
        return totalcnt;
    }

    public void setTotalcnt(int totalcnt) {
        this.totalcnt = totalcnt;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
                ", orderId=" + orderId +
                '}';
    }
}