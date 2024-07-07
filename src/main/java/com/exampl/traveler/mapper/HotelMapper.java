package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.HotelVO;
import com.exampl.traveler.vo.MemberVO;
import com.exampl.traveler.vo.UserOrderVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HotelMapper {
    List<HotelVO> getAllHotels();
    List<HotelVO> getHotelsByPlace(@Param("country") String country);
    HotelVO getHotelById(@Param("hotelNO") String hotelNO);
    void insertOrder(UserOrderVO userOrderVO);
}
