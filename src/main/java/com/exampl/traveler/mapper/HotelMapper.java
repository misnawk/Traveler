package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.HotelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HotelMapper {
    List<HotelVO> getAllHotels();

    List<HotelVO> getHotelsByPlace(@Param("place") String place);

    HotelVO getHotelById(@Param("hotelNO") String hotelNO);
}
