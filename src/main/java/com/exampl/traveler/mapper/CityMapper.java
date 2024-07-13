package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.CityVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityMapper {
    //도시 정보
    CityVO getCityByNumber(int cityNO);

    CityVO getCityByName(String cityName);

}