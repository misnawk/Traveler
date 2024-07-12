package com.exampl.traveler.service;

import com.exampl.traveler.mapper.PackageMapper;
import com.exampl.traveler.vo.UserOrderVO;
import com.exampl.traveler.vo.PackageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PackageService {

    private static final Logger log = LoggerFactory.getLogger(PackageService.class);

    @Autowired
    private PackageMapper packageMapper;

    public List<PackageVO> getAllPackages() {
        return packageMapper.getAllPackages();
    }

    public PackageVO getPackageById(String id) {
        return packageMapper.getPackageById(id);
    }

    public UserOrderVO createOrder(String packageNO, int peopleCount, String userId) {
        log.info("createOrder 메서드 시작 - 패키지 번호: {}", packageNO);

        PackageVO packageVO = getPackageById(packageNO);
        if (packageVO == null) {
            log.error("패키지를 찾을 수 없음: {}", packageNO);
            throw new RuntimeException("패키지를 찾을 수 없습니다: " + packageNO);
        }

        String diaryTitle = packageVO.getPackageTitle();
        log.info("주문 생성 중 - 사용자: {}, 패키지 제목: {}", userId, diaryTitle);

        // 현재 날짜를 가져옴
        Date orderDate = new Date();

        // UserOrderVO 객체 생성 및 값 설정
        UserOrderVO order = new UserOrderVO();
        order.setUserId(userId);
        order.setComNO(packageNO);
        order.setBinCate("4"); // 패키지 카테고리
        order.setTotalCnt(peopleCount);
        order.setOrderDate(orderDate);

        log.info("데이터베이스에 주문 삽입: {}", order);

        // 주문 삽입 및 orderId 가져오기
        packageMapper.insertOrder(order);

        log.info("주문 삽입 완료 - 주문 ID: {}", order.getOrderId());

        // Diary 삽입 (패키지의 출발 날짜와 종료 날짜를 사용)
        packageMapper.insertDiary(userId, order.getOrderId(), packageVO.getStartDateTime(),packageVO.getEndDateTime(), diaryTitle);

        log.info("다이어리 항목 삽입 완료 - 주문 ID: {}", order.getOrderId());

        return order;
    }
}
