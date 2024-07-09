package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.BusinessVO;
import com.exampl.traveler.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {

    // 일반회원
    // 수정한 회원정보 업데이트
    boolean proUpdate(MemberVO vo);

    // 수정한 비밀번호 업데이트
    boolean pwInsert(MemberVO vo);

    //기업회원
    // 수정한 회원정보 업데이트
    boolean binProUpdate(BusinessVO vo);

    // 수정한 비밀번호 업데이트
    boolean binPwUpdate(BusinessVO vo);
}
