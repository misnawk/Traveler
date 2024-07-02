package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.boardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    //메인페이에서 5개만 가져오게
    List<boardVO> getBoard();
    // 게시판 페이지에서 페이지 작업한것
    List<boardVO> getBoardPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    int getTotalBoardCount();
}