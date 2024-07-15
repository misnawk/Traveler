package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.AirVO;
import com.exampl.traveler.vo.BusinessVO;
import com.exampl.traveler.vo.PackageVO;
import com.exampl.traveler.vo.TicketVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessMapper {
    void binIdInsert(BusinessVO businessVO);

    void insertAir(AirVO airVO);

    void insertTicket(TicketVO ticketVO);

    void insertPackage(PackageVO packageVO);

}