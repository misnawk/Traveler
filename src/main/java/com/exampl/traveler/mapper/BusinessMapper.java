package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessMapper {
    // 수정한 기업회원 업데이트
    boolean binProUpdate(BusinessVO vo);

    // 수정한 비밀번호 업데이트
    boolean binPwUpdate(BusinessVO vo);

    void binIdInsert(BusinessVO businessVO);

    void insertAir(AirVO airVO);

    void binInsertHotel(HotelVO hotelVO);

    void insertTicket(TicketVO ticketVO);

    void insertPackage(PackageVO packageVO);

}