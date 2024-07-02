package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.HotelVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelMapper {
    List<HotelVO> getAllHotels();
}
