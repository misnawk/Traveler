package com.exampl.traveler.controller;

import com.exampl.traveler.service.LoginService;
import com.exampl.traveler.service.MyPageService;
import com.exampl.traveler.vo.BusinessVO;
import com.exampl.traveler.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BusinessController {
    public final LoginService loginService;
    public final MyPageService myPageService;

    // 기업 정보 수정하기 페이지로 이동
    @GetMapping("binpage/editor/{id}")
    public String proEditor(@PathVariable("id") String id, Model model){
        BusinessVO vo = loginService.binSelectOne(id);
        model.addAttribute("vo",vo);

        return "business/binEditor";
    }

    // 수정한 회원정보 업데이트
    @PostMapping("binpage/editor/update")
    public String proUpdate(BusinessVO vo){
        myPageService.binProUpdate(vo);

        return "redirect:/binpage/"+vo.getBinID();
    }

    // 현재 사용중인 비밀번호 확인
    @PostMapping("binpage/pw/check")
    public ResponseEntity<Boolean> binPwCheck(@RequestParam("id") String id,
                                           @RequestParam("pw") String pw,
                                           BusinessVO vo){
        boolean result = false;

        vo.setBinID(id);
        vo.setBinPW(pw);

        if(loginService.binLoginCheck(vo)){
            result = true;
        } else {
            result = false;
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 수정한 비밀번호 업데이트
    @PostMapping("binpage/pw/update")
    public String binPwUdate(@RequestParam("newPW") String pw ,
                             @RequestParam("id") String id, BusinessVO vo){

        vo.setBinID(id);
        vo.setBinPW(pw);

        myPageService.binPwUpdate(vo);
        return "redirect:/binpage/"+id;
    }

    // 항공 글작성 페이지로 이동
    @GetMapping("/binpage/airline")
    public String airWrite(){
        return "/business/airWrite";
    }

    // 숙박 글작성 페이지로 이동
    @GetMapping("/binpage/hotel")
    public String hotelWrite(){
        return "/business/hotelWrite";
    }

    // 티켓 글작성 페이지로 이동
    @GetMapping("/binpage/tick")
    public String tickWrite(){
        return "/business/tickWrite";
    }

    // 패키지 글작성 페이지로 이동
    @GetMapping("/binpage/packge")
    public String packWrite(){
        return "/business/packWrite";
    }

    // 비밀번호 변경 페이지로 이동
    @GetMapping("binpage/pw/{id}")
    public String binPwEditor(@PathVariable("id") String id){
        return "/business/binEditorPw";
    }
}
