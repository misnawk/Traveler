package com.exampl.traveler.service;

import com.exampl.traveler.mapper.HotelMapper;
import com.exampl.traveler.vo.HotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    public List<HotelVO> getAllHotels() {
        return hotelMapper.getAllHotels();
    }

    public List<HotelVO> getHotelsByPlace(String place){
        return hotelMapper.getHotelsByPlace(place);
    }

    public HotelVO getHotelById(String hotelNO) {
        return hotelMapper.getHotelById(hotelNO);
    }

}
