package com.exampl.traveler.service;

import com.exampl.traveler.mapper.AirMapper;
import com.exampl.traveler.vo.AirVO;
import com.exampl.traveler.vo.SeatVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirService {
    private final AirMapper airMapper;

    public List<AirVO> findOneWayAir(String departure, String destination, String departureDate) {
        return airMapper.findOneWayAir(departure, destination, departureDate);
    }

    public List<AirVO> findDepartureAir(String departure, String destination, String departureDate) {
        return airMapper.findDepartureAir(departure, destination, departureDate);
    }

    public List<AirVO> findReturnAir(String departure, String destination, String returnDate) {
        return airMapper.findReturnAir(departure, destination, returnDate);
    }

    public List<AirVO> findRoundTripAir(String departure, String destination, String departureDate, String returnDate) {
        List<AirVO> departureAir = airMapper.findDepartureAir(departure, destination, departureDate);
        List<AirVO> returnAir = airMapper.findReturnAir(destination, departure, returnDate);
        departureAir.addAll(returnAir);
        return departureAir;
    }

    public AirVO getAirByAirNo(String airNo) {
        return airMapper.getAirByAirNo(airNo);
    }


}