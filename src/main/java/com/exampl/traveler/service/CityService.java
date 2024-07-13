package com.exampl.traveler.service;

import com.exampl.traveler.mapper.CityMapper;
import com.exampl.traveler.vo.CityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;
    //도시정보
    public CityVO getCityByNumber(int cityNO) {
        return cityMapper.getCityByNumber(cityNO);
    }

    public CityVO getCityByName(String cityName) {
        return cityMapper.getCityByName(cityName);
    }
}