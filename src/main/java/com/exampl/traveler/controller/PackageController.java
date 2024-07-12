package com.exampl.traveler.controller;

import com.exampl.traveler.service.PackageService;
import com.exampl.traveler.vo.UserOrderVO;
import com.exampl.traveler.vo.PackageVO;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(PackageController.class);

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
        log.info("orderPackage 메서드 시작 - 패키지 ID: {}", id);
        String userId = (String) session.getAttribute("id");
        if (userId == null) {
            log.warn("사용자가 로그인하지 않았습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "로그인이 필요합니다."));
        }

        try {
            log.info("주문 생성 중 - 사용자: {}, 패키지 ID: {}, 인원수: {}", userId, id, peopleCount);
            UserOrderVO order = packageService.createOrder(id, peopleCount, userId);
            log.info("주문 생성 완료 - 주문 ID: {}", order.getOrderId());
            return ResponseEntity.ok(Collections.singletonMap("orderId", order.getOrderId()));
        } catch (RuntimeException e) {
            log.error("주문 생성 중 오류 발생: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            log.error("주문 처리 중 예기치 않은 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "주문 처리 중 오류가 발생했습니다: " + e.getMessage()));
        }
    }
}
