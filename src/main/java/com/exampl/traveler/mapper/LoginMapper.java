package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginMapper {
    // 회원 전체 조회
    List<MemberVO> selectAll();

    // 로그인 체크
    boolean loginCheck(MemberVO vo);

    // 아이디 중복 체크
    boolean idCheck(String id);

    // 회원가입
    boolean idInsert(MemberVO vo);
}
