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


    //편도 비행기찾기
    public List<AirVO> findOneWayAir(String departure, String destination, String departureDate) {
        return airMapper.findOneWayAir(departure, destination, departureDate);
    }
    //왕복비행기 가는편찾기
    public List<AirVO> findRoundTripAirs(String departure, String destination, String departureDate) {
        return airMapper.findRoundTripAir(departure, destination, departureDate);
    }
    //왕복 오눈편찾기
    public List<AirVO> findRoundTripReturnAirs(String departure, String destination, String returnDate) {
        return airMapper.findRoundTripReturnAir(departure, destination, returnDate);
    }


    //해당 비행기NO로 해당비행기의 모든정보 가져옴
    public AirVO getAirByAirlineNo(String airlineNO) {
        return airMapper.getAirByAirlineNo(airlineNO);
    }

    //해당 항공편의 해당 비행기NO로 좌석의 여부를 알아냄
    public List<String> getAvailableSeats(String airlineNo) {
        return airMapper.getAvailableSeats(airlineNo);
    }


    //좌석 테이블에 해당 비행기와 좌석번호와 좌석여부와 유저의 아이디 와 비행기타입을 보냄
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
    //좌석 예약한것을 order테이블에 넘김
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

    //좌석 예약한것을 diary 테이블에 넘김
    @Transactional
    public boolean insertDiary(String userId, int orderId, Date allDay, Date goDay, Date backDay, String diaryTitle, String tripType) {
        try {
            int result = airMapper.insertDiary(userId, orderId, allDay, goDay, backDay, diaryTitle, tripType);
            return result > 0;
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert diary", e);
        }
    }


    //예약한 항공권이 왕복인지 또는 편도인지 구분하고 만들어놓은 insertOrder 메서드와 insertOderDiary 를 구분해서 실행함
    @Transactional
    public boolean reserveSeatAndCreateDiary(String userId, String airlineNo, String seatNumber, String tripType, Date useDate, Date returnDate, String airTitle) {
        try {
            // 1. Insert order
            int orderId = insertOrder(userId, seatNumber, airlineNo, useDate);

            // 2. Insert diary
            Date allDay = null, goDay = null, backDay = null;
            if ("oneway".equals(tripType)) {
                allDay = useDate;
            } else if ("roundtrip".equals(tripType)) {
                goDay = useDate;
                backDay = returnDate;
            } else {
                throw new IllegalArgumentException("Invalid trip type: " + tripType);
            }

            return insertDiary(userId, orderId, allDay, goDay, backDay, airTitle, tripType);
        } catch (Exception e) {
            throw new RuntimeException("Failed to reserve seat and create diary", e);
        }
    }
}