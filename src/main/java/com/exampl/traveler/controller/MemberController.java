package com.exampl.traveler.controller;

import com.exampl.traveler.service.MemberService;
import com.exampl.traveler.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원 전체 조회
    @GetMapping("user")
    public String selectAll(Model model){
        List<MemberVO> user = memberService.selectAll();
        model.addAttribute("user", user);
        return "user";
    }

    // 로그인 페이지
    @GetMapping("login")
    public String login(){
        return "login/login";
    }

    // 로그인 체크
    @PostMapping("user/login")
    public ResponseEntity<Boolean> loginCheck(@RequestParam(value = "id") String id, @RequestParam(value = "pw") String pw){
        boolean result = false;

        if(memberService.loginCheck(id, pw)){
            result = true;
        } else {
            result = false;
        }


        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 회원가입 페이지
    @GetMapping("signUp")
    public String signUp(){
        return"login/signUp";
    }

    // 아이디 중복체크
    @PostMapping("user/check")
    public ResponseEntity<Boolean> idCheck(@RequestParam("id") String id){
        boolean result = true;

        // 전달 받은 id값이 이미 존재한지 체크
        if(memberService.idCheck(id)){
            result = false;
        } else {
            result = true;
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 회원가입
    @PostMapping("user/insert")
    public String userInsert(MemberVO vo,
                              @RequestParam(value="year") String year,
                              @RequestParam(value="month") String month,
                              @RequestParam(value="day") String day){

        month = String.format("%2s", month).replace(" ", "0");
        day = String.format("%2s", day).replace(" ", "0");

        vo.setUserBirth(year+"-"+month+"-"+day);
        System.out.println(vo);

        memberService.idInsert(vo);
        return "redirect:/login";
    }
}
