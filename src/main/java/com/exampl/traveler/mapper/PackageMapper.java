package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.OrdersVO;
import com.exampl.traveler.vo.PackageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface PackageMapper {

    List<PackageVO> getAllPackages();

    PackageVO getPackageById(@Param("id") String id);

    void insertOrder(OrdersVO order);

    void insertDiary(@Param("userId") String userId, @Param("orderId") int orderId, @Param("goDay") Date goDay, @Param("diaryTitle") String diaryTitle);

}
