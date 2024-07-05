package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.AirVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AirMapper {
    List<AirVO> findOutboundAirs(
            @Param("departure") String departure,
            @Param("destination") String destination,
            @Param("departureDate") String departureDate
    );

    List<AirVO> findReturnAirs(
            @Param("departure") String departure,
            @Param("destination") String destination,
            @Param("returnDate") String returnDate
    );

    List<AirVO> findOneWayAirs(
            @Param("departure") String departure,
            @Param("destination") String destination,
            @Param("departureDate") String departureDate
    );
}
