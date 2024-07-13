package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.MemberVO;
import com.exampl.traveler.vo.UserOrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {


    // 회원별 주문건 select
    List<UserOrderVO> orderSelectID(String id);

    // 수정한 회원정보 업데이트
    boolean proUpdate(MemberVO vo);

    // 수정한 비밀번호 업데이트
    boolean pwInsert(MemberVO vo);

}
