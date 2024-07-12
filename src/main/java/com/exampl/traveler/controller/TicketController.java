package com.exampl.traveler.controller;

import com.exampl.traveler.service.TicketService;
import com.exampl.traveler.vo.TicketVO;
import com.exampl.traveler.vo.UserOrderVO;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<TicketVO> getTicketsJson() {
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

    @PostMapping("/ticket/order")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody UserOrderVO orderVO, HttpSession session) {
        log.info("createOrder 메서드 시작");
        log.info("Received orderVO: {}", orderVO);
        String userId = (String) session.getAttribute("id");
        log.info("User ID from session: {}", userId);
        if (userId == null) {
            log.info("User not logged in");
            return ResponseEntity.ok(Map.of("loggedIn", false, "success", false));
        }

        try {
            orderVO.setUserId(userId);
            orderVO.setOrderDate(new Date()); // Set the order date
            orderVO.setBinCate("3"); // Set the default bincate
            log.info("Calling ticketService.insertOrder");
            ticketService.insertOrder(orderVO);
            log.info("Order inserted successfully");
            return ResponseEntity.ok(Map.of("success", true, "loggedIn", true));
        } catch (Exception e) {
            log.error("Error while creating order", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "loggedIn", true, "error", e.getMessage() != null ? e.getMessage() : "Unknown error"));
        }
    }
}
