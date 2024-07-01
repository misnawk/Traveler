package com.exampl.traveler.service;

import com.exampl.traveler.mapper.TicketMapper;
import com.exampl.traveler.vo.TicketVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    public List<TicketVO> getAllTickets() {
        return ticketMapper.getAllTickets();
    }
}
