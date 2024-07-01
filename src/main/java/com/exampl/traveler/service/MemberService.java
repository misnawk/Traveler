package com.exampl.traveler.service;

import com.exampl.traveler.mapper.MemberMapper;
import com.exampl.traveler.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberMapper memberMapper){
        this.memberMapper = memberMapper;
    }

    // 회원 전체 조회
    public List<MemberVO> selectAll(){
        return memberMapper.selectAll();
    }

    // 로그인 체크
    public boolean loginCheck(String id, String pw){
        return memberMapper.loginCheck(id, pw);
    }

    // 아이디 중복 체크
    public boolean idCheck(String id){
        return memberMapper.idCheck(id);
    }

    // 회원가입
    public boolean idInsert(MemberVO vo){
        return memberMapper.idInsert(vo);
    }
}
