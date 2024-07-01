package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.boardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<boardVO> getAllBoard();

}
