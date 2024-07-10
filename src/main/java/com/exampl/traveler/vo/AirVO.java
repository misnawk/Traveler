package com.exampl.traveler.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AirVO {
    private String airlineNo;
    private String airNo;
    private String airTitle;
    private String departureAirport;
    private String arrivalAirport;
    private String airCompany;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private int airPrice;
    private String cityNo;
    private int totalSeats;
    private int availableSeats;
}
