package com.exampl.traveler.service;

import com.exampl.traveler.mapper.AirMapper;
import com.exampl.traveler.vo.AirVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class AirService {

    @Autowired
    private AirMapper airMapper;

    public List<AirVO> getAllAirs() {
        return airMapper.getAllAirs();
    }

    public List<AirVO> searchAirs(String departure, String destination, LocalDate departureDate, int passengers) {
        return airMapper.searchAirs(departure, destination, departureDate, passengers);
    }
}