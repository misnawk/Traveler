package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.DiaryVO;
import com.exampl.traveler.vo.UserOrderVO;
import com.exampl.traveler.vo.PackageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PackageMapper {

    List<PackageVO> getAllPackages();

    //메인페이지 불러오기
    List<PackageVO> getPackages();

    //나라페이지에서 조회
    List<PackageVO> getPackagesNat(String keyword);

    PackageVO getPackageById(@Param("id") String id);

    void insertOrder(UserOrderVO order);

    void insertDiary(Map<String, Object> diaryParams);

    Integer getLastInsertId();
}