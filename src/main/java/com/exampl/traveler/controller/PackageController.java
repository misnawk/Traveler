package com.exampl.traveler.controller;

import com.exampl.traveler.service.PackageService;
import com.exampl.traveler.vo.PackageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping("/packages")
    public String getPackages(Model model) {
        List<PackageVO> packages = packageService.getAllPackages();

        // Debugging information
        System.out.println("Packages: " + packages);

        model.addAttribute("packages", packages);
        return "package/packageMain";
    }

    @GetMapping("/packages/{id}")
    public String getPackageDetail(@PathVariable("id") String id, Model model) {
        PackageVO packageVO = packageService.getPackageById(id);

        // Debugging information
        System.out.println("Package: " + packageVO);

        if (packageVO != null) {
            model.addAttribute("package", packageVO);
            return "package/packageDetail";
        } else {
            // 패키지가 없는 경우 에러 페이지로 리다이렉트
            return "redirect:/error";
        }
    }


}
