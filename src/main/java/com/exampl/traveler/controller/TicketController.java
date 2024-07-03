package com.exampl.traveler.controller;

import com.exampl.traveler.service.TicketService;
import com.exampl.traveler.vo.TicketVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/api/tickets")
    @ResponseBody
    public List<TicketVO> getTicketsJson() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/ticket/details/{binID}")
    public String ticketDetails(@PathVariable("binID") String binID, Model model) {
        TicketVO ticket = ticketService.getTicketByBinID(binID);
        if (ticket == null) {
            return "error";
        }
        model.addAttribute("ticket", ticket);
        return "ticket/ticketDetails";
    }

    @PostMapping("/reserveTicket")
    @ResponseBody
    public ResponseEntity<?> reserveTicket(@RequestParam("binID") String binID, HttpSession session) {
        String userId = (String) session.getAttribute("id");
        if (userId == null) {
            return ResponseEntity.ok().body(Map.of("loggedIn", false));
        }

        boolean reserved = ticketService.reserveTicket(userId, binID);
        return ResponseEntity.ok().body(Map.of("loggedIn", true, "reserved", reserved));
    }
}