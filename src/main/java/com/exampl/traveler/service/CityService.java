package com.exampl.traveler.service;

import com.exampl.traveler.mapper.CityMapper;
import com.exampl.traveler.vo.CityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    public CityVO getCityByNumber(int cityNO) {
        return cityMapper.getCityByNumber(cityNO);
    }
}