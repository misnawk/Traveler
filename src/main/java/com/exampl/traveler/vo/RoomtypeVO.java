package com.exampl.traveler.vo;

import lombok.Data;


@Data
public class RoomtypeVO {
    private String hotelNO;
    private String roomName;
    private String roomImg;
    private String roomFacility;
    private int roomMax;

    public RoomtypeVO() {}

    public RoomtypeVO(String hotelNO, String roomName, String roomImg, String roomFacility, int roomMax) {
        this.hotelNO = hotelNO;
        this.roomName = roomName;
        this.roomImg = roomImg;
        this.roomFacility = roomFacility;
        this.roomMax = roomMax;
    }

    @Override
    public String toString() {
        return "RoomtypeVO [hotelNO=" + hotelNO + ", roomName=" + roomName + ", roomImg=" + roomImg + ", roomFacility=" + roomFacility + ", roomMax=" + roomMax + "]";
    }
}

