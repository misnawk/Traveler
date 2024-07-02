package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.boardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<boardVO> getBoard();

    List<boardVO> getBoardPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    int getTotalBoardCount();
}