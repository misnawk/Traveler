package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    void increaseViewCount(int boardNo);
    //메인페이에서 5개만 가져오게
    List<BoardVO> getBoard();
    // 게시판 페이지에서 페이지 작업한것
    List<BoardVO> getBoardPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    int getTotalBoardCount();

    void insertBoard(BoardVO boardVO);

    BoardVO selectOne(@Param("boardNo") int boardNo);

}