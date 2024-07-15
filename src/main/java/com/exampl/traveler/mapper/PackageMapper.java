package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.UserOrderVO;
import com.exampl.traveler.vo.PackageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface PackageMapper {

    List<PackageVO> getAllPackages();

    //메인페이지 불러오기
    List<PackageVO> getPackages();

    //나라페이지에서 조회
    List<PackageVO> getPackagesNat(String keyword);

    PackageVO getPackageById(@Param("id") String id);

    void insertOrder(UserOrderVO order);

    void insertDiary(@Param("userId") String userId, @Param("orderId") int orderId, @Param("goDay") Date goDay, @Param("diaryTitle") String diaryTitle);

}
