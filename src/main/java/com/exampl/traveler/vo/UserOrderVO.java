package com.exampl.traveler.vo;

import lombok.Data;

import java.util.Date;

@Data
import java.util.Date;

public class UserOrderVO {
    private int orderId; // 자동 증가 ID
    private String userId;
    private String comNo;
    private String bincate;
    private int totalCnt;
    private Date orderDate;
    private String diaryTitle; // 패키지 제목
    private String comNO;
    private String binID;
    private int totalcnt;
    private Date orderdate;

    // 기본 생성자
    public UserOrderVO() {
    }

    public UserOrderVO(String userId, String comNo, String bincate, int totalCnt, Date orderDate, String diaryTitle) {
    public UserOrderVO(String userId, String comNO, String binID, int totalcnt, Date orderdate) {
        this.userId = userId;
        this.comNo = comNo;
        this.bincate = bincate;
        this.totalCnt = totalCnt;
        this.orderDate = orderDate;
        this.diaryTitle = diaryTitle;
        this.comNO = comNO;
        this.binID = binID;
        this.totalcnt = totalcnt;
        this.orderdate = orderdate;
    }
}

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComNO() {
        return comNO;
    }

    public void setComNO(String comNO) {
        this.comNO = comNO;
    }

    public String getBinID() {
        return binID;
    }

    public void setBinID(String binID) {
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

    @Override
    public String toString() {
        return "UserOrderVO{" +
                "userId='" + userId + '\'' +
                ", comNO='" + comNO + '\'' +
                ", binID='" + binID + '\'' +
                ", totalcnt=" + totalcnt +
                ", orderdate=" + orderdate +
                '}';
    }


}