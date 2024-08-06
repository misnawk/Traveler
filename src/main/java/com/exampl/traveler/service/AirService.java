package com.exampl.traveler.service;

import com.exampl.traveler.controller.SeatAlreadyReservedException;
import com.exampl.traveler.mapper.AirMapper;
import com.exampl.traveler.vo.AirVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AirService {
    private final AirMapper airMapper;

    public List<AirVO> findOneWayAir(String departure, String destination, String departureDate) {
        return airMapper.findOneWayAir(departure, destination, departureDate);
    }

    public List<AirVO> findRoundTripAirs(String departure, String destination, String departureDate) {
        return airMapper.findRoundTripAir(departure, destination, departureDate);
    }

    public List<AirVO> findRoundTripReturnAirs(String departure, String destination, String returnDate) {
        return airMapper.findRoundTripReturnAir(departure, destination, returnDate);
    }

    public AirVO getAirByAirlineNo(String airlineNO) {
        return airMapper.getAirByAirlineNo(airlineNO);
    }

    public List<String> getAvailableSeats(String airlineNo) {
        return airMapper.getAvailableSeats(airlineNo);
    }

    @Transactional
    public void reserveSeat(String airlineNo, String seatNumber, String userId, String tripType) {
        List<String> availableSeats = getAvailableSeats(airlineNo);
        if (!availableSeats.contains(seatNumber)) {
            throw new SeatAlreadyReservedException("이미 예약된 좌석입니다: " + seatNumber);
        }

        int updatedRows = airMapper.updateSeatAvailability(airlineNo, seatNumber, false, userId, tripType);
        if (updatedRows == 0) {
            throw new SeatAlreadyReservedException("좌석 예약에 실패했습니다: " + seatNumber);
        }
    }

    @Transactional
    public int insertOrder(String userId, String seatNumber, String airlineNo, Date useDate) {
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("userId", userId);
            paramMap.put("comNO", airlineNo);
            paramMap.put("seatNumber", seatNumber);
            paramMap.put("useDate", useDate);

            airMapper.insertOrder(paramMap);
            return (int) paramMap.get("orderId");
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert order", e);
        }
    }

    @Transactional
    public boolean insertDiary(String userId, int orderId, Date allDay, Date goDay, Date backDay, String diaryTitle, String tripType) {
        try {
            int result = airMapper.insertDiary(userId, orderId, allDay, goDay, backDay, diaryTitle, tripType);
            return result > 0;
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert diary", e);
        }
    }

    @Transactional
    public boolean reserveSeatAndCreateDiary(String userId, String airlineNo, String seatNumber, String tripType, Date departureDate, Date arrivalDate, String airTitle) {
        try {
            int orderId = insertOrder(userId, seatNumber, airlineNo, departureDate);

            Date allDay = null, goDay = null, backDay = null;
            if ("oneway".equals(tripType)) {
                allDay = departureDate;
                goDay = departureDate;
                backDay = arrivalDate;
            } else if ("roundtrip".equals(tripType)) {
                goDay = departureDate;
                backDay = arrivalDate;
            } else {
                throw new IllegalArgumentException("Invalid trip type: " + tripType);
            }

            return insertDiary(userId, orderId, allDay, goDay, backDay, airTitle, tripType);
        } catch (Exception e) {
            throw new RuntimeException("Failed to reserve seat and create diary", e);
        }
    }
}