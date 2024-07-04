package com.exampl.traveler.controller;

import com.exampl.traveler.service.LoginService;
import com.exampl.traveler.service.MyPageService;
import com.exampl.traveler.vo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
public class MypageController {
    private final LoginService loginService;
    private final MyPageService myPageService;

    // 회원정보 수정 페이지
    @GetMapping("mypage/editor/{id}")
    public String proEditor(@PathVariable("id") String id, Model model){
        MemberVO vo = loginService.selectOne(id);
        model.addAttribute("vo",vo);

        return "/mypage/editor";
    }

    // 비밀번호 수정 페이지
    @GetMapping("mypage/pw/{id}")
    public String editorPW(@PathVariable("id") String id){

        return "/mypage/editorPW";
    }

    // 현재 사용중인 비밀번호 확인
    @PostMapping("mypage/pw/check")
    public ResponseEntity<Boolean> pwCheck(@RequestParam("pw") String pw,
                                           MemberVO vo,
                                           HttpServletRequest request){
        boolean result = false;

        HttpSession session = request.getSession();
        Object id = session.getAttribute("id");

        vo.setUserID((String) id);
        vo.setUserPW(pw);

        if(loginService.loginCheck(vo)){
            result = true;
        } else {
            result = false;
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 수정한 비밀번호 저장
    @PostMapping("mypage/pw/insert")
    public String pwInsert(@RequestParam("newPW") String pw ,HttpServletRequest request, MemberVO vo){
        HttpSession session = request.getSession();
        Object id = session.getAttribute("id");

        vo.setUserID((String) id);
        vo.setUserPW(pw);

        myPageService.pwInsert(vo);
        return "redirect:/mypage/"+id;
    }
}
