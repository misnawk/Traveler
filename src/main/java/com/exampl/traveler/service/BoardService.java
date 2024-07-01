package com.exampl.traveler.service;

import com.exampl.traveler.mapper.BoardMapper;
import com.exampl.traveler.vo.boardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardMapper boardMapper;

    public List<boardVO> getAllBoard(){
        return boardMapper.getAllBoard();
    }

}
