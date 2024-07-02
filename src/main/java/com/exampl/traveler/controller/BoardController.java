package com.exampl.traveler.controller;

import com.exampl.traveler.service.BoardService;
import com.exampl.traveler.vo.boardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    BoardService boardService;

    //게시판 메인 페이지 한페이지에 5개씩 페이지 작업까지 완료
    @RequestMapping("/board")
    public String board(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        int pageSize = 5;
        List<boardVO> boardPage = boardService.getBoardPage(page, pageSize);
        int totalBoards = boardService.getTotalBoardCount();
        int totalPages = (int) Math.ceil((double) totalBoards / pageSize);

        model.addAttribute("board", boardPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "/board/boardList";
    }
}
