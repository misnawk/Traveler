package com.exampl.traveler.service;

import com.exampl.traveler.mapper.AdminMapper;
import com.exampl.traveler.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    public final AdminMapper adminMapper;

    @Autowired
    public AdminService(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    // 회원관리 select 검색
    public List<MemberVO> searchID(String data){
        return adminMapper.searchID(data);
    }
    public List<MemberVO> searchName(String data){
        return adminMapper.searchName(data);
    }
    public List<MemberVO> searchTell(String data){
        return adminMapper.searchTell(data);
    }
    public List<MemberVO> searchEmail(String data){
        return adminMapper.searchEmail(data);
    }

    // 기업관리 select 검색
    public List<BusinessVO> binSearchID(String data){
        return adminMapper.binSearchID(data);
    }
    public List<BusinessVO> binSearchName(String data){
        return adminMapper.binSearchName(data);
    }
    public List<BusinessVO> binSearchCode(String data){
        return adminMapper.binSearchCode(data);
    }
    public List<BusinessVO> binSearchTell(String data){
        return adminMapper.binSearchTell(data);
    }
    public List<BusinessVO> binSearchEmail(String data){
        return adminMapper.binSearchEmail(data);
    }

    // 상품관리
    public List<AirVO> airSelectAll(){
        return adminMapper.airSelectAll();
    }
    public List<HotelVO> hotelSelectAll(){
        return adminMapper.hotelSelectAll();
    }
    public List<TicketVO> tickSelectAll(){
        return adminMapper.tickSelectAll();
    }
    public List<PackageVO> packSelectAll(){
        return adminMapper.packSelectAll();
    }

    //상세페이지
    public List<AirVO> airSelectID(String binID){
        return adminMapper.airSelectID(binID);
    }
    public List<HotelVO> hotelSelectID(String binID){
        return adminMapper.hotelSelectID(binID);
    }
    public List<TicketVO> tickSelectID(String binID){
        return adminMapper.tickSelectID(binID);
    }
    public List<PackageVO> packSelectID(String binID){
        return adminMapper.packSelectID(binID);
    }

    // 주문관리
    public List<UserOrderVO> orderSelectAll(){
        return adminMapper.orderSelectAll();
    }
}