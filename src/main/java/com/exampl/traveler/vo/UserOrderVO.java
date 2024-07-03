package com.exampl.traveler.vo;

public class UserOrderVO {

    private String userId;
    private String binID;

    // 기본 생성자
    public UserOrderVO() {
    }

    public UserOrderVO(String userId, String binID) {
        this.userId = userId;
        this.binID = binID;
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBinID() {
        return binID;
    }

    public void setBinID(String binID) {
        this.binID = binID;
    }

    @Override
    public String toString() {
        return "UserOrderVO{" +
                "userId='" + userId + '\'' +
                ", binID='" + binID + '\'' +
                '}';
    }
}