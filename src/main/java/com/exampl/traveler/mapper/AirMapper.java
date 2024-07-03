package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.AirVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AirMapper {

    //클라이언트가 입력한 출발지와 도착지를 받아온다. (왕복)
    List<AirVO> findAirlineRound(
            @Param("departure") String departure, //출발지
            @Param("destination") String destination, //도착지
            @Param("departureDate") String departureDate, //가는날
            @Param("returnDate") String returnDate //오는날
    );

            //클라이언트가 입력한 출발지와 도착지를 받아온다. (편도)
    List<AirVO>findAirlineOne(
            @Param("departure") String departure, //출발지
            @Param("destination") String destination, //도착지
            @Param("departureDate") String departureDate //가는날
    );
    // 입력데이터에 맞는 비행기의 ID를 가져오고 ID를 기준으로 항공권의 정보를 가져온다.
    AirVO findAirlineById(@Param("airNO") String airNO);

}
