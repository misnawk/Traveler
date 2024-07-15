package com.exampl.traveler.service;

import com.exampl.traveler.mapper.HotelMapper;
import com.exampl.traveler.vo.HotelVO;
import com.exampl.traveler.vo.RoomtypeVO;
import com.exampl.traveler.vo.UserOrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public UserOrderVO createOrder(String userId, String hotelNO, String binCate, int peopleCount, Date useDate) {
        UserOrderVO order = new UserOrderVO();
        order.setUserId(userId);
        order.setComNO(hotelNO);
        order.setBinCate(binCate);
        order.setTotalCnt(peopleCount);
        order.setOrderDate(new Date());  // 현재 날짜 설정
        order.setUseDate(useDate);


        hotelMapper.insertOrder(order);

        if (order.getOrderId() == 0) {
            throw new RuntimeException("주문 ID 생성에 실패했습니다.");
        }

        return order;
    }

    @Transactional
    public void createDiary(String userId, int orderId, Date goday, Date backday, String hotelName) {
        Map<String, Object> diaryParams = new HashMap<>();
        diaryParams.put("userId", userId);
        diaryParams.put("orderID", orderId);
        diaryParams.put("goday", goday);
        diaryParams.put("backday", backday);
        diaryParams.put("diaryTitle", hotelName);

        hotelMapper.insertDiary(diaryParams);
    }

    public void addRoom(RoomtypeVO roomtypeVO) {
        hotelMapper.insertRoom(roomtypeVO);
    }

    public List<Map<String, Object>> getFacilitiesByHotel(String hotelNO) {
        return hotelMapper.selectFacilitiesByHotel(hotelNO);
    }

    public List<RoomtypeVO> getRoomsByFacilityAndHotel(String hotelNO, String facility) {
        return hotelMapper.selectRoomsByFacilityAndHotel(hotelNO, facility);
    }
}