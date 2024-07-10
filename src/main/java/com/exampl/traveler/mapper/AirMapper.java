package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.AirVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AirMapper {
    List<AirVO> findOneWayAir(@Param("departure") String departure,
                              @Param("destination") String destination,
                              @Param("departureDate") String departureDate);

    List<AirVO> findDepartureAir(@Param("departure") String departure,
                                 @Param("destination") String destination,
                                 @Param("departureDate") String departureDate);

    List<AirVO> findReturnAir(@Param("departure") String departure,
                              @Param("destination") String destination,
                              @Param("returnDate") String returnDate);

    AirVO getAirByAirNo(@Param("airNo") String airNo);

    AirVO getAirById(@Param("airlineNo") String airlineNo);

    int decreaseAvailableSeats(@Param("airlineNo") String airlineNo);

    void updateTotalAndAvailableSeats(@Param("airlineNo") String airlineNo,
                                      @Param("totalSeats") int totalSeats,
                                      @Param("availableSeats") int availableSeats);
}