package com.exampl.traveler.service;

import com.exampl.traveler.mapper.PackageMapper;
import com.exampl.traveler.vo.DiaryVO;
import com.exampl.traveler.vo.UserOrderVO;
import com.exampl.traveler.vo.PackageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //메인에서 조회
    public List<PackageVO> getPackages() {return packageMapper.getPackages();}
    //국가디테일 페이지에서 조회
    public List<PackageVO> getPackagesNat(String keyword){return packageMapper.getPackagesNat(keyword);}

    @Transactional
    public Integer createOrder(String userId, String packageNO, int peopleCount, Date useDate) {
        PackageVO packageVO = getPackageById(packageNO);
        if (packageVO == null) {
            throw new RuntimeException("패키지를 찾을 수 없습니다: " + packageNO);
        }

        UserOrderVO order = new UserOrderVO();
        order.setUserId(userId);
        order.setComNO(packageNO);
        order.setBinCate("4");
        order.setTotalCnt(peopleCount);
        order.setOrderDate(new Date());
        order.setUseDate(useDate);

        packageMapper.insertOrder(order);
        return packageMapper.getLastInsertId();
    }

    @Transactional
    public void createDiary(String userId, int orderId, Date useDate, Date backDate, String diaryTitle) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedGoDay = sdf.format(useDate);
        String formattedBackDay = sdf.format(backDate);

        Map<String, Object> diaryParams = new HashMap<>();
        diaryParams.put("userId", userId);
        diaryParams.put("orderId", orderId);
        diaryParams.put("goDay", formattedGoDay);
        diaryParams.put("backDay", formattedBackDay);
        diaryParams.put("diaryTitle", diaryTitle);

        packageMapper.insertDiary(diaryParams);
    }
}