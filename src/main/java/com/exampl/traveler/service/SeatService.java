package com.exampl.traveler.service;

import com.exampl.traveler.mapper.AirMapper;
import com.exampl.traveler.mapper.BookingMapper;
import com.exampl.traveler.mapper.SeatMapper;
import com.exampl.traveler.vo.AirVO;
import com.exampl.traveler.vo.BookingVO;
import com.exampl.traveler.vo.SeatVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatMapper seatMapper;
    private final BookingMapper bookingMapper;
    private final AirMapper airMapper;

    public List<SeatVO> getSeatsForAir(String airlineNo) {
        return seatMapper.getSeatsForAir(airlineNo);
    }

    @Transactional
    public boolean reserveSeat(String airlineNo, String seatNumber, String userId) {
        // 항공편 존재 여부 및 가용 좌석 확인
        AirVO air = airMapper.getAirById(airlineNo);
        if (air == null || air.getAvailableSeats() <= 0) {
            return false; // 항공편이 없거나 가용 좌석 없음
        }

        // 좌석 예약 가능 여부 확인 및 업데이트
        int updatedRows = seatMapper.updateSeatAvailability(airlineNo, seatNumber, false);
        if (updatedRows == 0) {
            return false; // 좌석이 이미 예약됨
        }

        // Booking 테이블에 예약 정보 삽입
        BookingVO booking = new BookingVO();
        booking.setUserId(userId);
        booking.setAirlineId(airlineNo);
        booking.setSeatNo(seatNumber);
        booking.setStatus("confirmed");
        bookingMapper.insertBooking(booking);

        // Airline 테이블의 availableSeats 감소
        airMapper.decreaseAvailableSeats(airlineNo);

        return true;
    }

    @Transactional
    public void createSeatsForNewFlight(String airlineNo) {
        List<String> seatNumbers = Arrays.asList(
                "1A", "1B", "1C", "1D", "1E",
                "2A", "2B", "2C", "2D", "2E",
                "32A", "32B", "32C", "32D", "32E"
        );

        for (String seatNumber : seatNumbers) {
            SeatVO seat = new SeatVO();
            seat.setAirlineNo(airlineNo);
            seat.setSeatNumber(seatNumber);
            seat.setIsAvailable(true);
            seatMapper.insertSeat(seat);
        }

        // 항공편의 총 좌석 수와 가용 좌석 수 업데이트
        int totalSeats = seatNumbers.size();
        airMapper.updateTotalAndAvailableSeats(airlineNo, totalSeats, totalSeats);
    }
}