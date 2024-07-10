package com.exampl.traveler.vo;

import lombok.Data;

@Data
public class BookingVO {
    private int bookingId;
    private String userId;
    private String airlineId;
    private String seatNo;
    private String status;
    private java.sql.Timestamp bookingDateTime;
}
