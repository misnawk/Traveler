package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginMapper {

    //// 일반회원 로그인 & 회원가입 mapper
    // 로그인 체크
    boolean loginCheck(MemberVO vo);

    // 아이디 중복 체크
    boolean idCheck(String id);

    // 회원가입
    boolean idInsert(MemberVO vo);

    // 회원 한명 찾기
    MemberVO selectOne(String id);

    //// 기업회원 로그인 & 회원가입 mapper
    // 기업 아이디 중복 체크
    boolean binIdCheck(String id);
}
