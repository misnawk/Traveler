package com.exampl.traveler.controller;

import com.exampl.traveler.service.PackageService;
import com.exampl.traveler.vo.TicketVO;
import com.exampl.traveler.vo.UserOrderVO;
import com.exampl.traveler.vo.PackageVO;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

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



    @PostMapping("/packages/{packageNO}/order")
    @ResponseBody
    public ResponseEntity<?> createOrder(@PathVariable("packageNO") String packageNO,
                                         @RequestBody Map<String, Object> requestData,
                                         HttpSession session) {
        String userId = (String) session.getAttribute("id");
        if (userId == null) {
            return ResponseEntity.ok().body(Map.of("loggedIn", false));
        }

        int peopleCount = Integer.parseInt(requestData.get("peopleCount").toString());

        try {
            PackageVO packages = packageService.getPackageById(packageNO);
            if (packages == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Package not found"));
            }
            Date useDate = packages.getStartDateTime();

            
            //오더
            Integer orderId = packageService.createOrder(userId, packageNO, peopleCount, useDate);
            if (orderId == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "Failed to create order"));
            }


            Date backDate = packages.getEndDateTime();

            // 다이어리
            packageService.createDiary(userId, orderId, useDate, backDate ,packages.getPackageTitle());

            return ResponseEntity.ok().body(Map.of(
                    "loggedIn", true,
                    "ordered", true,
                    "diaryCreated", true,
                    "orderId", orderId
            ));
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(Map.of(
                    "loggedIn", true,
                    "ordered", false,
                    "diaryCreated", false,
                    "error", e.getMessage()
            ));
        }
    }
}