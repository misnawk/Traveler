package com.exampl.traveler.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AirVO {
    private String airlineNO;
    private String binId;
    private String airTitle;
    private String departureAirport;
    private String arrivalAirport;
    private String airCompany;
    private Date departureDateTime;
    private Date arrivalDateTime;
    private int airPrice;
    private int cityNO;
    private String cityName;
}
