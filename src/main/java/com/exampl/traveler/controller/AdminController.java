package com.exampl.traveler.controller;

import com.exampl.traveler.service.*;
import com.exampl.traveler.vo.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
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
    public final CityService cityService;
    private final AirService airService;
    private final HotelService hotelService;
    private final TicketService ticketService;
    private final PackageService packageService;

    // 회원관리 select 검색
    @GetMapping("/user")
    public String userSearch(@RequestParam("option") String option,
                             @RequestParam("data") String data,
                             HttpSession httpSession,
                             Model model){
        String user = (String) httpSession.getAttribute("id");
        if(ObjectUtils.isEmpty(user) || !user.equals("admin")){
            return "/login/login";
        } else {
            List<MemberVO> vo = null;

            if (option.equals("userID")) {
                vo = adminService.searchID(data);
            } else if (option.equals("userName")) {
                vo = adminService.searchName(data);
            } else if (option.equals("userTell")) {
                vo = adminService.searchTell(data);
            } else if (option.equals("userEmail")) {
                vo = adminService.searchEmail(data);
            }

            model.addAttribute("vo", vo);
            return "/admin/admin";
        }
    }

    // 기업관리 페이지 이동
    @GetMapping("/business")
    public String business(HttpSession httpSession, Model model){
        String user = (String) httpSession.getAttribute("id");
        if(ObjectUtils.isEmpty(user) || !user.equals("admin")){
            return "/login/login";
        } else {
            List<BusinessVO> vo = loginService.binSelectAll();
            model.addAttribute("vo", vo);
            return "/admin/adminBin";
        }
    }

    // 기업관리 select 검색
    @GetMapping("/business/search")
    public String binSearch(@RequestParam(value = "option", required = false) String option,
                            @RequestParam(value = "data", required = false) String data,
                            HttpSession httpSession,
                            Model model){
        String user = (String) httpSession.getAttribute("id");
        if(ObjectUtils.isEmpty(user) || !user.equals("admin")){
            return "/login/login";
        } else {
            List<BusinessVO> vo = null;

            if (option.equals("binID")) {
                vo = adminService.binSearchID(data);
            } else if (option.equals("binName")) {
                vo = adminService.binSearchName(data);
            } else if (option.equals("binCode")) {
                vo = adminService.binSearchCode(data);
            } else if (option.equals("binTell")) {
                vo = adminService.binSearchTell(data);
            } else if (option.equals("binEmail")) {
                vo = adminService.binSearchEmail(data);
            }

            model.addAttribute("vo", vo);
            return "/admin/adminBin";
        }
    }

    // 상품관리 페이지 이동
    @GetMapping("/items")
    public String items(@RequestParam(value = "binCate", required = false) String binCate,
                        HttpSession httpSession,Model model){
        String user = (String) httpSession.getAttribute("id");
        if(ObjectUtils.isEmpty(user) || !user.equals("admin")){
            return "/login/login";
        } else {
            String type = null;

            if (binCate == null || binCate.equals("airline")) {
                List<AirVO> item = adminService.airSelectAll();
                type = "airline";

                for (int i = 0; i < item.size(); i++) {
                    System.out.println(item.get(i).getCityNO());
                    CityVO city = cityService.getCityByNumber(item.get(i).getCityNO());
                    item.get(i).setCityName(city.getCityName());
                }

                model.addAttribute("item", item);
                model.addAttribute("type", type);
            } else if (binCate.equals("hotel")) {
                List<HotelVO> item = adminService.hotelSelectAll();
                type = "hotel";

                model.addAttribute("item", item);
                model.addAttribute("type", type);
            } else if (binCate.equals("tick")) {
                List<TicketVO> item = adminService.tickSelectAll();
                type = "tick";

                model.addAttribute("item", item);
                model.addAttribute("type", type);
            } else if (binCate.equals("package")) {
                List<PackageVO> item = adminService.packSelectAll();
                type = "package";

                model.addAttribute("item", item);
                model.addAttribute("type", type);
            }

            return "/admin/adminItems";
        }
    }

    // 상세페이지
    @GetMapping("/detail")
    public String detail(@RequestParam(value = "binID", required = false) String binID,
                         HttpSession httpSession, Model model){
        String user = (String) httpSession.getAttribute("id");
        if(ObjectUtils.isEmpty(user) || !user.equals("admin")){
            return "/login/login";
        } else {
            BusinessVO vo = loginService.binSelectOne(binID);

            if (vo.getBinCate().equals("1")) {
                List<AirVO> items = adminService.airSelectID(binID);
                model.addAttribute("items", items);
            } else if (vo.getBinCate().equals("2")) {
                List<HotelVO> items = adminService.hotelSelectID(binID);
                model.addAttribute("items", items);
            } else if (vo.getBinCate().equals("3")) {
                List<TicketVO> items = adminService.tickSelectID(binID);
                model.addAttribute("items", items);
            } else if (vo.getBinCate().equals("4")) {
                List<PackageVO> items = adminService.packSelectID(binID);
                model.addAttribute("items", items);
            }

            model.addAttribute("vo", vo);
            return "/admin/adminDetail";
        }
    }

    // 결제 페이지 이동
    @GetMapping("/orders")
    public String orders(HttpSession httpSession, Model model){
        String user = (String) httpSession.getAttribute("id");
        if(ObjectUtils.isEmpty(user) || !user.equals("admin")){
            return "/login/login";
        } else {
            List<UserOrderVO> orders = adminService.orderSelectAll();

            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getComNO().startsWith("A")) {
                    AirVO item = airService.getAirByAirlineNo(orders.get(i).getComNO());
                    orders.get(i).setPrice(item.getAirPrice() * orders.get(i).getTotalCnt());
                    orders.get(i).setTitle(item.getAirTitle());
                } else if (orders.get(i).getComNO().startsWith("h")) {
                    HotelVO item = hotelService.getHotelById(orders.get(i).getComNO());
                    orders.get(i).setPrice(item.getHotelPrice() * orders.get(i).getTotalCnt());
                    orders.get(i).setTitle(item.getHotelName());
                } else if (orders.get(i).getComNO().startsWith("T")) {
                    TicketVO item = ticketService.getTicketByTickNO(orders.get(i).getComNO());
                    orders.get(i).setPrice(item.getTickPrice() * orders.get(i).getTotalCnt());
                    orders.get(i).setTitle(item.getTickTitle());
                } else if (orders.get(i).getComNO().startsWith("P")) {
                    PackageVO item = packageService.getPackageById(orders.get(i).getComNO());
                    orders.get(i).setPrice(item.getPackagePrice() * orders.get(i).getTotalCnt());
                    orders.get(i).setTitle(item.getPackageTitle());
                }

            }

            model.addAttribute("orders", orders);

            return "/admin/adminOrders";
        }
    }

}
