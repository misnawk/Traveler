package com.exampl.traveler.service;

import com.exampl.traveler.mapper.HotelMapper;
import com.exampl.traveler.vo.HotelVO;
import com.exampl.traveler.vo.MemberVO;
import com.exampl.traveler.vo.RoomtypeVO;
import com.exampl.traveler.vo.UserOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
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
    public UserOrderVO createOrder(String userId, String hotelNO, int peopleCount) {
        UserOrderVO order = new UserOrderVO();
        order.setUserId(userId);
        order.setComNO(hotelNO);
        order.setBinID("2");  // 호텔 예약을 의미하는 고정 값
        order.setTotalcnt(peopleCount);
        order.setOrderdate(new Date());  // 현재 날짜 설정

        hotelMapper.insertOrder(order);

        if (order.getOrderId() == 0) {
            throw new RuntimeException("주문 ID 생성에 실패했습니다.");
        }

        return order;
    }

    @Transactional
    public void createDiary(String userId, int orderId, Date goday, Date backday, String hotelName, String hotelText, Date checkInTime) {
        Map<String, Object> diaryParams = new HashMap<>();
        diaryParams.put("userId", userId);
        diaryParams.put("orderID", orderId);
        diaryParams.put("goday", goday);
        diaryParams.put("backday", backday);
        diaryParams.put("diaryTitle", hotelName);
        diaryParams.put("diaryText", hotelText);
        diaryParams.put("diaryTime", checkInTime);

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