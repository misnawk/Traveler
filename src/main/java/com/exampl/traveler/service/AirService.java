package com.exampl.traveler.service;

import com.exampl.traveler.mapper.AirMapper;
import com.exampl.traveler.vo.AirVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirService {

    private final AirMapper airMapper;

    @Autowired
    public AirService(AirMapper airMapper) {
        this.airMapper = airMapper;
    }

    public List<AirVO> findAirlineRound(String departure, String destination, String departureDate, String returnDate) {
        return airMapper.findAirlineRound(departure, destination, departureDate, returnDate);
    }

    public List<AirVO> findAirlineOne(String departure, String destination, String departureDate) {
        return airMapper.findAirlineOne(departure, destination, departureDate);
    }

    public AirVO findAirlineById(String airNO) {
        return airMapper.findAirlineById(airNO);
    }
}