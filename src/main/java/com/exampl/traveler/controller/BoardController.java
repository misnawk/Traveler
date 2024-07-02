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
