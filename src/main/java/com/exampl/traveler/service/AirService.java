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

    public List<AirVO> findOutboundAirs(String departure, String destination, String departureDate) {
        return airMapper.findOutboundAirs(departure, destination, departureDate);
    }

    public List<AirVO> findReturnAirs(String departure, String destination, String returnDate) {
        return airMapper.findReturnAirs(departure, destination, returnDate);
    }

    public List<AirVO> findOneWayAirs(String departure, String destination, String departureDate) {
        return airMapper.findOneWayAirs(departure, destination, departureDate);
    }
}
