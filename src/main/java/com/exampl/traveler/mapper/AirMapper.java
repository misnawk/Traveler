package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.AirVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface AirMapper {
    List<AirVO> findOneWayAir(@Param("departure") String departure,
                              @Param("destination") String destination,
                              @Param("departureDate") String departureDate);

    List<AirVO> findRoundTripAir(@Param("departure") String departure,
                                 @Param("destination") String destination,
                                 @Param("departureDate") String departureDate);

    List<AirVO> findRoundTripReturnAir(@Param("departure") String departure,
                                       @Param("destination") String destination,
                                       @Param("returnDate") String returnDate);

    AirVO getAirByAirlineNo(@Param("airlineNo") String airlineNo);

    List<String> getAvailableSeats(@Param("airlineNo") String airlineNo);

    int updateSeatAvailability(@Param("airlineNo") String airlineNo,
                               @Param("seatNumber") String seatNumber,
                               @Param("isAvailable") boolean isAvailable,
                               @Param("userId") String userId,
                               @Param("tripType") String tripType);


    int insertOrder(Map<String, Object> paramMap);


    int insertDiary(@Param("userId") String userId,
                    @Param("orderId") int orderId,
                    @Param("allDay") Date allDay,
                    @Param("goDay") Date goDay,
                    @Param("backDay") Date backDay,
                    @Param("diaryTitle") String diaryTitle,
                    @Param("tripType") String tripType);


    int getLastInsertId();
}
