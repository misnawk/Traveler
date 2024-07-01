package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.AirVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface AirMapper {
    List<AirVO> getAllAirs();

    List<AirVO> searchAirs(@Param("departure") String departure,
                           @Param("destination") String destination,
                           @Param("departureDate") LocalDate departureDate,
                           @Param("passengers") int passengers);
}