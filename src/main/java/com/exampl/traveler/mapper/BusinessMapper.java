package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.BusinessVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessMapper {
    void binIdInsert(BusinessVO businessVO);
}