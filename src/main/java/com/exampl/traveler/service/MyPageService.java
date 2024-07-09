package com.exampl.traveler.service;

import com.exampl.traveler.mapper.MyPageMapper;
import com.exampl.traveler.vo.BusinessVO;
import com.exampl.traveler.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MyPageMapper myPageMapper;

    // 일반회원
    // 수정한 회원정보 업데이트
    public boolean proUpdate(MemberVO vo){
        return myPageMapper.proUpdate(vo);
    }

    // 수정한 비밀번호 업데이트
    public boolean pwInsert(MemberVO vo){
        return myPageMapper.pwInsert(vo);
    }

    // 기업회원
    // 수정한 회원정보 업데이트
    public boolean binProUpdate(BusinessVO vo){
        return  myPageMapper.binProUpdate(vo);
    }
    // 수정한 비밀번호 업데이트
    public boolean binPwUpdate(BusinessVO vo){
        return myPageMapper.binPwUpdate(vo);
    }
}
