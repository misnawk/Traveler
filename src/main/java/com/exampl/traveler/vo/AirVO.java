package com.exampl.traveler.vo;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import java.math.BigDecimal;
import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name = "airline")
public class AirVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int airlineID;

    private String natNO; // 나라 일련번호
    private String airNO; // 항공편 일련번호
    private String airTitle; // 항공편명
    private String departureAirport; // 출발공항
    private String arrivalAirport; // 도착공항
    private String aircompany; // 공항사
    private LocalDateTime departureTime; //출발일시
    private LocalDateTime arrivalTime; //도착일시
    private String airSeat; // 항공편 좌석
    private double airPrice; // 항공편 가격

}