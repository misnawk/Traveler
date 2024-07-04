package com.exampl.traveler.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ScheduleController {
    @RequestMapping("/schedule")
    public String schedule(Model model){
        return "/schedule/schedule";
    }
}
