package com.exampl.traveler.controller;

import com.exampl.traveler.service.BoardService;
import com.exampl.traveler.service.CityService;
import com.exampl.traveler.service.LoginService;
import com.exampl.traveler.service.NationService;
import com.exampl.traveler.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final LoginService loginService;
    private final CityService cityService;
    private final NationService nationService;
    @Autowired
    BoardService boardService;
    //메인페이지 게시판에 5개만 보이게 설정
    @RequestMapping("/")
    public String main(Model model){
        List<BoardVO> board = boardService.getBoard();
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

    // admin 페이지
    @GetMapping("admin")
    public String admin(Model model){
        List<MemberVO> vo = loginService.selectAll();
        model.addAttribute("vo",vo);
        return "/admin/admin";
    }

    @GetMapping("/search")
    public String search(@RequestParam("data") String query) {
        // 도시 검색
        CityVO city = cityService.getCityByName(query);
        if (city != null) {
            return "redirect:/city?cityNO=" + city.getCityNO();
        }

        // 국가 검색
        NationVO nation = nationService.getNationByName(query);
        if (nation != null) {
            return "redirect:/nation/detail/" + nation.getNatNO();
        }

        // 검색 결과가 없을 경우
        return "redirect:/noResults";
    }


    // 일반회원 마이페이지
    @GetMapping("mypage/{id}")
    public String myPage(@PathVariable("id") String id, Model model){
        MemberVO vo = loginService.selectOne(id);
        model.addAttribute("vo",vo);

        return "/mypage/mypage";
    }

    // 기업회원 관리자
    @GetMapping("binpage/{id}")
    public String binPage(@PathVariable("id") String id, Model model){
        BusinessVO vo = loginService.binSelectOne(id);
        model.addAttribute("vo",vo);

        return "/business/binpage";
    }

}
