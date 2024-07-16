package com.exampl.traveler.controller;

import com.exampl.traveler.service.BoardService;
import com.exampl.traveler.service.CityService;
import com.exampl.traveler.service.LoginService;
import com.exampl.traveler.service.NationService;
import com.exampl.traveler.vo.*;
import com.exampl.traveler.service.*;
import com.exampl.traveler.vo.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.mysql.cj.conf.PropertyKey.logger;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final LoginService loginService;
    @Autowired
    private CityService cityService;
    private final MyPageService myPageService;
    private final AirService airService;
    private final HotelService hotelService;
    private final TicketService ticketService;
    private final PackageService packageService;
    private final AdminService adminService;



    @Autowired
    BoardService boardService;


    private final NationService nationService;
    //메인페이지 게시판에 5개만 보이게 설정
    @RequestMapping("/")
    public String main(Model model){
        List<BoardVO> board = boardService.getBoard();
        model.addAttribute("board",board);
        List<PackageVO> packages = packageService.getPackages();
        model.addAttribute("packages", packages);

        return "/main/main";
    }
    @RequestMapping("/nation")
    public String nation(Model model){
        List<PackageVO> packages = packageService.getPackages();
        model.addAttribute("packages", packages);
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
    public String admin(HttpSession httpSession, Model model){
        String user = (String) httpSession.getAttribute("id");
        if(ObjectUtils.isEmpty(user) || !user.equals("admin")){
            return "/login/login";
        } else {
            List<MemberVO> vo = loginService.selectAll();
            model.addAttribute("vo", vo);
            return "/admin/admin";
        }
    }

    // 일반회원 마이페이지
    @GetMapping("mypage/{id}")
    public String myPage(@PathVariable("id") String id, HttpSession httpSession, Model model){

        String user = (String) httpSession.getAttribute("id");

        if(ObjectUtils.isEmpty(user) || !user.equals(id)){
            return "/login/login";
        } else {
            MemberVO vo = loginService.selectOne(id);
            model.addAttribute("vo", vo);

            List<UserOrderVO> orders = myPageService.orderSelectID(id);

            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getComNO().startsWith("A")) {
                    AirVO item = airService.getAirByAirlineNo(orders.get(i).getComNO());
                    orders.get(i).setTitle(item.getAirTitle());
                } else if (orders.get(i).getComNO().startsWith("h")) {
                    HotelVO item = hotelService.getHotelById(orders.get(i).getComNO());
                    orders.get(i).setTitle(item.getHotelName());
                } else if (orders.get(i).getComNO().startsWith("T")) {
                    TicketVO item = ticketService.getTicketByTickNO(orders.get(i).getComNO());
                    orders.get(i).setTitle(item.getTickTitle());
                } else if (orders.get(i).getComNO().startsWith("P")) {
                    PackageVO item = packageService.getPackageById(orders.get(i).getComNO());
                    orders.get(i).setTitle(item.getPackageTitle());
                }
            }

            model.addAttribute("orders", orders);

            return "/mypage/mypage";
        }
    }

    // 기업회원 관리자
    @GetMapping("binpage/{id}")
    public String binPage(@PathVariable("id") String id, HttpSession httpSession, Model model){
        String user = (String) httpSession.getAttribute("binID");

        if(ObjectUtils.isEmpty(user) || !user.equals(id)){
            return "/login/binLogin";
        } else {
            BusinessVO vo = loginService.binSelectOne(id);

            if (vo.getBinCate().equals("1")) {
                List<AirVO> item = adminService.airSelectID(id);

                for (int i = 0; i < item.size(); i++) {
                    System.out.println(item.get(i).getCityNO());
                    CityVO city = cityService.getCityByNumber(item.get(i).getCityNO());
                    item.get(i).setCityName(city.getCityName());
                }

                model.addAttribute("item", item);
            } else if (vo.getBinCate().equals("2")) {
                List<HotelVO> item = adminService.hotelSelectID(id);
                model.addAttribute("item", item);
            } else if (vo.getBinCate().equals("3")) {
                List<TicketVO> item = adminService.tickSelectID(id);
                System.out.println("출력 : " + item);
                model.addAttribute("item", item);
            } else if (vo.getBinCate().equals("4")) {
                List<PackageVO> item = adminService.packSelectID(id);
                model.addAttribute("item", item);
            }
            model.addAttribute("vo", vo);

            return "/business/binpage";
        }
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
}
