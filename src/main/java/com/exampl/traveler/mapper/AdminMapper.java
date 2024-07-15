package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    // 일반회원 select 검색
    List<MemberVO> searchID(String data);
    List<MemberVO> searchName(String data);
    List<MemberVO> searchTell(String data);
    List<MemberVO> searchEmail(String data);

    // 기업회원 select 검색
    List<BusinessVO> binSearchID(String data);
    List<BusinessVO> binSearchName(String data);
    List<BusinessVO> binSearchCode(String data);
    List<BusinessVO> binSearchTell(String data);
    List<BusinessVO> binSearchEmail(String data);

    // 상품관리
    List<AirVO> airSelectAll();
    List<HotelVO> hotelSelectAll();
    List<TicketVO> tickSelectAll();
    List<PackageVO> packSelectAll();

    // 상세페이지
    List<AirVO> airSelectID(String binID);
    List<HotelVO> hotelSelectID(String binID);
    List<TicketVO> tickSelectID(String binID);
    List<PackageVO> packSelectID(String binID);

    // 주문관리
    List<UserOrderVO> orderSelectAll();
}