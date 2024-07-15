package com.exampl.traveler.controller;

import com.exampl.traveler.service.LoginService;
import com.exampl.traveler.vo.BusinessVO;
import com.exampl.traveler.vo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
public class LoginController {
    private final LoginService loginService;

    // 로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    //// 일반회원 로그인 & 회원가입 Controller
    // 로그인 페이지
    @GetMapping("login")
    public String login(){
        return "login/login";
    }

    // 로그인 체크
    @PostMapping("user/login")
    public ResponseEntity<Boolean> loginCheck(@RequestParam("id") String id,
                                              @RequestParam("pw") String pw,
                                              MemberVO vo,
                                              HttpServletRequest request){

        boolean result = false;
        HttpSession session = request.getSession();

        vo.setUserID(id);
        vo.setUserPW(pw);

        if(loginService.loginCheck(vo)){
            MemberVO getVO = loginService.selectOne(id);
            session.setMaxInactiveInterval(3600); //세션 1시간 유지
            session.setAttribute("id",id);
            session.setAttribute("name",getVO.getUserName());
            result = true;
        } else {
            result = false;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 회원 로그인 페이지
    @GetMapping("signUp")
    public String signUp(){
        return "/login/signUp";
    }

    // 아이디 중복체크
    @PostMapping("user/check")
    public ResponseEntity<Boolean> idCheck(@RequestParam("id") String id){
        boolean result = true;

        // 전달 받은 id값이 이미 존재한지 체크
        if(loginService.idCheck(id)){
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
                              @RequestParam(value="day") String day,
                              @RequestParam(value="userID") String userID,
                              @RequestParam(value="userTell") String userTell){

        month = String.format("%2s", month).replace(" ", "0");
        day = String.format("%2s", day).replace(" ", "0");

        vo.setUserID(userID.trim());
        vo.setUserBirth(year+"-"+month+"-"+day);
        vo.setUserTell(userTell.replaceAll("[^0-9]","").trim());

        loginService.idInsert(vo);
        return "redirect:/login";
    }


    //// 기업회원 로그인 & 회원가입 Controller
    // 기업 로그인 페이지
    @GetMapping("binLogin")
    public String binLogin(){
        return "/login/binLogin";
    }

    // 로그인 체크
    @PostMapping("bin/login")
    public ResponseEntity<Boolean> binLoginCheck(@RequestParam("id") String id,
                                                @RequestParam("pw") String pw,
                                                 BusinessVO vo,
                                                 HttpServletRequest request){

        // ResponseEntity :  HttpStatus, HttpHeaders, HttpBody 포함된 어노테이션
        boolean result = false;
        HttpSession session = request.getSession();

        vo.setBinID(id);
        vo.setBinPW(pw);

        if(loginService.binLoginCheck(vo)){
            BusinessVO getVO = loginService.binSelectOne(id);
            session.setMaxInactiveInterval(3600); //세션 1시간 유지
            session.setAttribute("binID",id);
            session.setAttribute("binName",getVO.getBinName());
            session.setAttribute("binCate",getVO.getBinCate());
            session.setAttribute("user", getVO);
            result = true;
        } else {
            result = false;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 기업 회원가입 페이지
    @GetMapping("binSignUp")
    public String binSign(){
        return "/login/binSignUp";
    }

    // 기업 아이디 중복 체크
    @PostMapping("bin/idCheck")
    public ResponseEntity<Boolean> binIdCheck(@RequestParam("id") String id){
        boolean result = true;

        // 전달 받은 id값이 이미 존재한지 체크
        if(loginService.binIdCheck(id)){
            result = false;
        } else {
            result = true;
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 기업 회원가입
    @PostMapping("bin/insert")
    public String binInsert(BusinessVO vo,
                            @RequestParam(value="binID") String binID,
                            @RequestParam(value="binTell") String binTell,
                            @RequestParam(value="binCode") String binCode){

        vo.setBinID(binID.trim());
        vo.setBinTell(binTell.replaceAll("[^0-9]","").trim());
        vo.setBinCode(binCode.replaceAll("[^0-9]","").trim());
        loginService.binIdInsert(vo);
        return "redirect:/binLogin";
    }

    @PostMapping("bin/codeCheck")
    public ResponseEntity<Boolean> codeCheck(@RequestParam("code") String code){
        boolean result = true;

        // 전달 받은 id값이 이미 존재한지 체크
        if(loginService.codeCheck(code)){
            result = false;
        } else {
            result = true;
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
