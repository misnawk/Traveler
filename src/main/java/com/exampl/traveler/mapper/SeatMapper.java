package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.SeatVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SeatMapper {
    List<SeatVO> getSeatsForAir(@Param("airlineNo") String airlineNo);

    int updateSeatAvailability(@Param("airlineNo") String airlineNo,
                               @Param("seatNumber") String seatNumber,
                               @Param("isAvailable") boolean isAvailable);

    int updateSeatAvailabilityWithLock(@Param("airlineNo") String airlineNo,
                                       @Param("seatNumber") String seatNumber,
                                       @Param("isAvailable") boolean isAvailable);

    List<SeatVO> getAllSeats();

    int updateSeat(SeatVO seat);

    int insertSeat(SeatVO seat);
}