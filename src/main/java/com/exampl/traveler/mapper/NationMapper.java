package com.exampl.traveler.mapper;


import com.exampl.traveler.vo.NationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NationMapper {


    //국가 정보
    NationVO selectNationById(String natNO);

    NationVO getNationByName(String nationName);
}
