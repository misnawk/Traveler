package com.exampl.traveler.vo;

import lombok.Data;

@Data
public class SeatVO {
    private int seatID;
    private String airlineNo;
    private String seatNumber;
    private boolean isAvailable;

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
