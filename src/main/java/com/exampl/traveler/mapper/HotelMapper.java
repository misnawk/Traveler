package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.HotelVO;
import com.exampl.traveler.vo.MemberVO;
import com.exampl.traveler.vo.RoomtypeVO;
import com.exampl.traveler.vo.UserOrderVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface HotelMapper {
    List<HotelVO> getAllHotels();
    List<HotelVO> getHotelsByPlace(@Param("country") String country);
    HotelVO getHotelById(@Param("hotelNO") String hotelNO);
    void insertOrder(UserOrderVO userOrderVO);
    void insertDiary(Map<String, Object> params);

    void insertRoom(RoomtypeVO roomtypeVO);

    List<Map<String, Object>> selectFacilitiesByHotel(String hotelNO);

    List<RoomtypeVO> selectRoomsByFacilityAndHotel(@Param("hotelNO") String hotelNO, @Param("facility") String facility);


}