package com.exampl.traveler.controller;

import com.exampl.traveler.service.TicketService;
import com.exampl.traveler.vo.TicketVO;
import jakarta.servlet.http.HttpSession;
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
public class TicketController {

    private static final Logger log = LoggerFactory.getLogger(TicketController.class);

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
    public List<TicketVO> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/ticket/details/{tickNO}")
    public String ticketDetails(@PathVariable("tickNO") String tickNO, Model model) {
        TicketVO ticket = ticketService.getTicketByTickNO(tickNO);
        if (ticket == null) {
            return "error";
        }
        model.addAttribute("ticket", ticket);
        return "ticket/ticketDetails";
    }

    @PostMapping("/reserveTicket")
    @ResponseBody
    public ResponseEntity<?> reserveTicket(@RequestBody Map<String, Object> requestData, HttpSession session) {
        String userId = (String) session.getAttribute("id");
        if (userId == null) {
            return ResponseEntity.ok().body(Map.of("loggedIn", false));
        }

        String ticketNO = (String) requestData.get("ticketNO");
        int quantity = Integer.parseInt(requestData.get("quantity").toString());

        TicketVO ticket = ticketService.getTicketByTickNO(ticketNO);
        Date useDate = ticket.getTickDate();

        try {
            Integer orderId=ticketService.createOrder(userId, ticketNO, quantity, useDate);

            if (orderId != null) {
                ticketService.createDiary(userId, orderId, useDate, ticket.getTickTitle());

                return ResponseEntity.ok().body(Map.of(
                        "loggedIn", true,
                        "ordered", true,
                        "diaryCreated", true
                ));
            } else {
                return ResponseEntity.badRequest().body(Map.of(
                        "loggedIn", true,
                        "ordered", false,
                        "diaryCreated", false,
                        "error", "Order ID not found"
                ));
            }
        } catch (Exception e) {
            log.error("Error reserving ticket", e);
            return ResponseEntity.badRequest().body(Map.of(
                    "loggedIn", true,
                    "ordered", false,
                    "diaryCreated", false
            ));
        }
    }
}
