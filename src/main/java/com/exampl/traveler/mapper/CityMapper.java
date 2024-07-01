package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.CityVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityMapper {

    CityVO getCityByNumber(int cityNO);
}