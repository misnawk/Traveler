package com.exampl.traveler.service;

import com.exampl.traveler.mapper.LoginMapper;
import com.exampl.traveler.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    private final LoginMapper loginMapper;

    @Autowired
    public LoginService(LoginMapper loginMapper){
        this.loginMapper = loginMapper;
    }

    // 회원 전체 조회
    public List<MemberVO> selectAll(){
        return loginMapper.selectAll();
    }

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
}
