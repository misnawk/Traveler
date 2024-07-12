package com.exampl.traveler.controller;

import com.exampl.traveler.service.AdminService;
import com.exampl.traveler.service.LoginService;
import com.exampl.traveler.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    public final AdminService adminService;
    public final LoginService loginService;

    // 회원관리 select 검색
    @GetMapping("/user")
    public String userSearch(@RequestParam("option") String option,
                             @RequestParam("data") String data,
                             Model model){

        List<MemberVO> vo = null;

        if(option.equals("userID")){
            vo = adminService.searchID(data);
        } else if(option.equals("userName")){
            vo = adminService.searchName(data);
        } else if(option.equals("userTell")){
            vo = adminService.searchTell(data);
        } else if(option.equals("userEmail")){
            vo = adminService.searchEmail(data);
        }

        model.addAttribute("vo",vo);
        return "/admin/admin";
    }

    // 기업관리 페이지 이동
    @GetMapping("/business")
    public String business(Model model){
        List<BusinessVO> vo = loginService.binSelectAll();
        model.addAttribute("vo",vo);
        return "/admin/adminBin";
    }

    // 기업관리 select 검색
    @GetMapping("/business/search")
    public String binSearch(@RequestParam(value = "option", required = false) String option,
                           @RequestParam(value = "data", required = false) String data,
                           Model model){
        List<BusinessVO> vo = null;

        if(option.equals("binID")){
            vo = adminService.binSearchID(data);
        } else if(option.equals("binName")){
            vo = adminService.binSearchName(data);
        } else if(option.equals("binCode")){
            vo = adminService.binSearchCode(data);
        } else if(option.equals("binTell")){
            vo = adminService.binSearchTell(data);
        } else if(option.equals("binEmail")){
            vo = adminService.binSearchEmail(data);
        }

        model.addAttribute("vo", vo);
        return "/admin/adminBin";
    }

    // 상품관리 페이지 이동
    @GetMapping("/items")
    public String items(@RequestParam(value = "binCate", required = false) String binCate, Model model){
        String type = null;

        if(binCate == null || binCate.equals("airline")) {
            List<AirVO> vo = adminService.airSelectAll();
            type = "airline";

            model.addAttribute("vo",vo);
            model.addAttribute("type", type);
        } else if(binCate.equals("hotel")){
            List<HotelVO> vo = adminService.hotelSelectAll();
            type = "hotel";

            model.addAttribute("vo",vo);
            model.addAttribute("type", type);
        } else if(binCate.equals("tick")){
            List<TicketVO> vo = adminService.tickSelectAll();
            type = "tick";

            model.addAttribute("vo",vo);
            model.addAttribute("type", type);
        } else if(binCate.equals("package")){
            List<PackageVO> vo = adminService.packSelectAll();
            type = "package";

            model.addAttribute("vo",vo);
            model.addAttribute("type", type);
        }

        return "/admin/adminItems";
    }

    // 상세페이지
    @GetMapping("/detail")
    public String detail(@RequestParam(value = "binID", required = false) String binID,
                         @RequestParam(value = "option", required = false) String option,
                         @RequestParam(value = "data", required = false) String data, Model model){

        BusinessVO vo = loginService.binSelectOne(binID);

        if(vo.getBinCate() == 1) {
            List<AirVO> items = adminService.airSelectID(binID);
            model.addAttribute("items", items);
        } else if(vo.getBinCate() == 2){
            List<HotelVO> items = adminService.hotelSelectID(binID);
            model.addAttribute("items", items);
        } else if(vo.getBinCate() == 3){
            List<TicketVO> items = adminService.tickSelectID(binID);
            model.addAttribute("items", items);
        } else if(vo.getBinCate() == 4){
            List<PackageVO> items = adminService.packSelectID(binID);
            model.addAttribute("items", items);
        }

        model.addAttribute("vo",vo);
        return "/admin/adminDetail";
    }

}
