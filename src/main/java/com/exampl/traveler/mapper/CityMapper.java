package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.CityVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityMapper {
    //도시 정보
    CityVO getCityByNumber(int cityNO);

    // 도시 이름으로 도시 정보 조회
    CityVO getCityByName(String cityName);
}