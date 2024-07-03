package com.exampl.traveler.service;

import com.exampl.traveler.mapper.BoardMapper;
import com.exampl.traveler.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;


@Service
public class BoardService {
    @Autowired
    private BoardMapper boardMapper;
    // 메인페이지 게시판 불러오기
    public List<BoardVO> getBoard() {
        return boardMapper.getBoard();
    }
    //게시판 저부불러오기
    public List<BoardVO> getBoardAll() {
        return boardMapper.getBoard();
    }
    //게시판 페이지 설정
    public List<BoardVO> getBoardPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return boardMapper.getBoardPage(offset, pageSize);
    }

    public int getTotalBoardCount() {
        return boardMapper.getTotalBoardCount();
    }


    public void saveBoard(BoardVO boardVO) {
        boardMapper.insertBoard(boardVO);
    }

    public BoardVO selectOne(int boardNo){
        return boardMapper.selectOne(boardNo);

    }



}