package com.exampl.traveler.service;

import com.exampl.traveler.mapper.LoginMapper;
import com.exampl.traveler.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final LoginMapper loginMapper;

    @Autowired
    public LoginService(LoginMapper loginMapper){
        this.loginMapper = loginMapper;
    }

    //// 일반회원 로그인 & 회원가입 Service
    // 로그인 체크
    public boolean loginCheck(MemberVO vo){
        return loginMapper.loginCheck(vo);
    }

    // 아이디 중복 체크
    public boolean idCheck(String id){
        return loginMapper.idCheck(id);
    }

    // 회원가입
    public boolean idInsert(MemberVO vo){
        return loginMapper.idInsert(vo);
    }

    // 회원 한명 찾기
    public MemberVO selectOne(String id){
        return loginMapper.selectOne(id);
    }

    //// 기업회원 로그인 & 회원가입 Service
    // 기업 로그인 체크
    public boolean binLoginCheck(BusinessVO vo){
        return loginMapper.binLoginCheck(vo);
    }

    // 기업 아이디 중복 체크
    public boolean binIdCheck(String id){
        return loginMapper.binIdCheck(id);
    }

    // 기업 회원가입
    public boolean binIdInsert(BusinessVO vo){
        return loginMapper.binIdInsert(vo);
    }

    // 기업 한개 찾기
    public BusinessVO binSelectOne(String id){
        return loginMapper.binSelectOne(id);
    }
}
