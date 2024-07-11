package com.exampl.traveler.controller;

import com.exampl.traveler.service.BoardService;
import com.exampl.traveler.service.NationService;
import com.exampl.traveler.vo.BoardVO;
import com.exampl.traveler.vo.NationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class NationController {
    private final NationService nationService;
    @Autowired
    BoardService boardService;
    @Autowired
    public NationController(NationService nationService) {
        this.nationService = nationService;
    }
    // 국가 페이지에서 국가 고유 번호 넘겨주면 받아서 국가 디테일 페이지로 이동
    @GetMapping("/nation/detail/{natNO}")
    public String getNationDetail(@PathVariable("natNO") String natNO, Model model) {
        NationVO nation = nationService.getNationDetails(natNO);
        model.addAttribute("nation", nation);

        String keyword = nation.getNatName();  // 국가명을 키워드로 사용
        List<BoardVO> board = boardService.getBoardByKeyword(keyword);
        model.addAttribute("board", board);

        return "/nation/natDetaile";
    }

}
