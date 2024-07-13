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
//    //게시판 저부불러오기
//    public List<BoardVO> getBoardAll() {
//        return boardMapper.getBoard();
//    }
    //게시판 페이지 설정
    public List<BoardVO> getBoardPage(Integer tripType, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        if(tripType == null){
        return boardMapper.getBoardPage(offset, pageSize);
        }else{
            return boardMapper.findBoardAllTripType(tripType,offset,pageSize);
        }
    }

    public int getTotalBoardCount() {
        return boardMapper.getTotalBoardCount();
    }
    //검색기능
    public List<BoardVO> search(String data,Integer tripType){
        if(tripType == null){
            return boardMapper.search(data);
        }else{
            return boardMapper.findsearchTripType(data,tripType);
        }

    }


    public void saveBoard(BoardVO boardVO) {
        boardMapper.insertBoard(boardVO);
    }

    public BoardVO selectOne(int boardNo){
        boardMapper.increaseViewCount(boardNo);
        return boardMapper.selectOne(boardNo);

    }

    public List<BoardVO> getBoardByKeyword(String keyword) {
        return boardMapper.getBoardByKeyword(keyword);  // 새로운 메서드 구현
    }



}