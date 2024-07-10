package com.exampl.traveler.service;

import com.exampl.traveler.mapper.PackageMapper;
import com.exampl.traveler.vo.UserOrderVO;
import com.exampl.traveler.vo.PackageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PackageService {

    @Autowired
    private PackageMapper packageMapper;

    public List<PackageVO> getAllPackages() {
        return packageMapper.getAllPackages();
    }

    public PackageVO getPackageById(String id) {
        return packageMapper.getPackageById(id);
    }

    public UserOrderVO createOrder(String packageNo, int peopleCount, String userId) {
        PackageVO packageVO = getPackageById(packageNo);
        if (packageVO == null) {
            throw new RuntimeException("패키지를 찾을 수 없습니다: " + packageNo);
        }

        String diaryTitle = packageVO.getPackageTitle();

        // 현재 날짜를 가져옴
        Date orderDate = new Date();

        // OrdersVO 객체 생성 및 값 설정
        UserOrderVO order = new UserOrderVO();
        order.setUserId(userId);
        order.setComNO(packageNo);
        order.setBincate("4"); // 패키지 카테고리
        order.setTotalcnt(peopleCount);
        order.setOrderdate(orderDate);

        // 주문 삽입 및 orderId 가져오기
        packageMapper.insertOrder(order);

        // Diary 삽입 (패키지의 출발 날짜와 시간을 사용)
        packageMapper.insertDiary(userId, order.getOrderId(), packageVO.getStartDateTime(), diaryTitle);

        return order;
    }
}
