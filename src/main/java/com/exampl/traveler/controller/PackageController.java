package com.exampl.traveler.controller;

import com.exampl.traveler.service.PackageService;
import com.exampl.traveler.vo.UserOrderVO;
import com.exampl.traveler.vo.PackageVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping("/packages")
    public String getPackages(Model model) {
        List<PackageVO> packages = packageService.getAllPackages();
        model.addAttribute("packages", packages);
        return "package/packageMain";
    }

    @GetMapping("/packages/{id}")
    public String getPackageDetail(@PathVariable("id") String id, Model model) {
        PackageVO packageVO = packageService.getPackageById(id);
        model.addAttribute("package", packageVO);
        return "package/packageDetail";
    }

    @PostMapping("/packages/{id}/order")
    @ResponseBody
    public ResponseEntity<?> orderPackage(@PathVariable("id") String id,
                                          @RequestParam("peopleCount") int peopleCount,
                                          HttpSession session) {
        String userId = (String) session.getAttribute("id");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "로그인이 필요합니다."));
        }

        try {
            UserOrderVO order = packageService.createOrder(id, peopleCount, userId);
            return ResponseEntity.ok(Collections.singletonMap("orderId", order.getOrderId()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "주문 처리 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }
}
