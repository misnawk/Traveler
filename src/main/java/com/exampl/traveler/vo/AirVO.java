package com.exampl.traveler.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirVO {
    private int airlineID;
    private String natNO;
    private String airNO;
    private String airTitle;
    private String departureAirport;
    private String arrivalAirport;
    private String aircompany;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String airSeat;
    private double airPrice;
}
