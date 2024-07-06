package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.PackageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PackageMapper {

    List<PackageVO> getAllPackages();

    PackageVO getPackageById(@Param("id") String id);
}
