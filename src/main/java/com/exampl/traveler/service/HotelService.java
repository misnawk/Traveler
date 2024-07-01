package com.exampl.traveler.service;

import com.exampl.traveler.mapper.HotelMapper;
import com.exampl.traveler.vo.HotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    public List<HotelVO> getAllHotels() {
        return hotelMapper.getAllHotels();
    }
}
