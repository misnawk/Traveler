package com.exampl.traveler.service;

import com.exampl.traveler.mapper.MyPageMapper;
import com.exampl.traveler.vo.MemberVO;
import com.exampl.traveler.vo.UserOrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MyPageMapper myPageMapper;

    // 회원별 주문건 select
    public List<UserOrderVO> orderSelectID(String id){
        return myPageMapper.orderSelectID(id);
    }

    // 수정한 회원정보 업데이트
    public boolean proUpdate(MemberVO vo){
        return myPageMapper.proUpdate(vo);
    }

    // 수정한 비밀번호 업데이트
    public boolean pwInsert(MemberVO vo){
        return myPageMapper.pwInsert(vo);
    }

}
