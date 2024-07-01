package com.exampl.traveler.controller;

import com.exampl.traveler.service.TicketService;
import com.exampl.traveler.vo.TicketVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/ticket")
    public String listTickets(Model model) {
        List<TicketVO> tickets = ticketService.getAllTickets();
        model.addAttribute("tickets", tickets);
        return "ticket/ticketMain";
    }
}
