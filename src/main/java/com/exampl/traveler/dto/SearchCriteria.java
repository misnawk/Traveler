package com.exampl.traveler.dto;

import lombok.Data;

@Data
public class SearchCriteria {
    private String departure;
    private String destination;
    private String departureDate;
    private String returnDate;
    private int passengers;
    private String tripType; // "oneway" or "roundtrip"
}