package com.exampl.traveler.service;

import com.exampl.traveler.mapper.HotelMapper;
import com.exampl.traveler.vo.HotelVO;
import com.exampl.traveler.vo.UserOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    public List<HotelVO> getAllHotels() {
        return hotelMapper.getAllHotels();
    }

    public List<HotelVO> getHotelsByPlace(String country) {
        return hotelMapper.getHotelsByPlace(country);
    }

    public HotelVO getHotelById(String hotelNO) {
        return hotelMapper.getHotelById(hotelNO);
    }

    @Transactional
    public void insertOrder(UserOrderVO userOrderVO) {
        hotelMapper.insertOrder(userOrderVO);
    }
}
