package com.exampl.traveler.controller;

import com.exampl.traveler.service.BoardService;
import com.exampl.traveler.vo.boardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    BoardService boardService;
    //메인페이지 게시판에 5개만 보이게 설정
    @RequestMapping("/")
    public String main(Model model){
        List<boardVO> board = boardService.getBoard();
        model.addAttribute("board",board);

        return "/main/main";
    }
    @RequestMapping("/nation")
    public String nation(Model model){

        return "/nation/nation";
    }
    @GetMapping("/header")
    public String Header(Model model) {
        return "header";
    }
    @GetMapping("/footer")
    public String Footer(Model model){
        return "foter";
    }

}
