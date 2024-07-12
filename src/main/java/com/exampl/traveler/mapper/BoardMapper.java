package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.BoardVO;
import com.exampl.traveler.vo.BookingVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    //메인페이에서 5개만 가져오게
    List<BoardVO> getBoard();
    // 게시판 페이지에서 페이지 작업한것
    List<BoardVO> getBoardPage(@Param("offset") int offset, @Param("pageSize") int pageSize);
    //패키지 자유여행 타입나누기
    List<BoardVO> findBoardAllTripType(@Param("tripType") Integer tripType,@Param("offset") int offset, @Param("pageSize")int pageSize);

    //검색기능
    List<BoardVO> search(String data);
    List<BoardVO> findsearchTripType(String data,Integer tripType);

    //조회수
    int getTotalBoardCount();
    //조회수 증가
    void increaseViewCount(int boardNo);
    void insertBoard(BoardVO boardVO);
    //상세보기
    BoardVO selectOne(@Param("boardNo") int boardNo);

    //나라마다 그나라에 관한 게시글
    List<BoardVO> getBoardByKeyword(String keyword);
}