package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {

    // 수정한 회원정보 업데이트
    boolean proUpdate(MemberVO vo);

    // 수정한 비밀번호 업데이트
    boolean pwInsert(MemberVO vo);

}
