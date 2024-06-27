package com.exampl.traveler.controller;

import com.exampl.traveler.service.NationService;
import com.exampl.traveler.vo.NationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NationController {
    private final NationService nationService;

    @Autowired
    public NationController(NationService nationService) {
        this.nationService = nationService;
    }

    @GetMapping("/nation/detail/{natNO}")
    public String getNationDetail(@PathVariable("natNO") String natNO, Model model) {
        NationVO nation = nationService.getNationById(natNO);
        model.addAttribute("nation", nation);
        return "/nation/natDetaile";
    }
}
