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

    public UserOrderVO(String userId, String comNO, String binID, int totalcnt, Date orderdate) {
        this.userId = userId;
        this.comNO = comNO;
        this.binID = binID;
        this.totalcnt = totalcnt;
        this.orderdate = orderdate;
    }

    // Getters and setters
    public String getUserId() {
        return userId;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "UserOrderVO{" +
                "userId='" + userId + '\'' +
                ", comNO='" + comNO + '\'' +
                ", binID='" + binID + '\'' +
                ", totalcnt=" + totalcnt +
                ", orderdate=" + orderdate +
                ", orderId=" + orderId +
                '}';
    }
}