package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {

    // 수정한 비밀번호 저장
    boolean pwInsert(MemberVO vo);
}
