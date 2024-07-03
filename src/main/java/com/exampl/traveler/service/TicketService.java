package com.exampl.traveler.service;

import com.exampl.traveler.mapper.TicketMapper;
import com.exampl.traveler.vo.TicketVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TicketService {

    private final TicketMapper ticketMapper;

    @Autowired
    public TicketService(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public List<TicketVO> getAllTickets() {
        return ticketMapper.getAllTickets();
    }

    public TicketVO getTicketByBinID(String binID) {
        return ticketMapper.getTicketByBinID(binID);
    }

    @Transactional
    public boolean reserveTicket(String userId, String binID) {
        try {
            int result = ticketMapper.reserveTicket(userId, binID);
            return result > 0;
        } catch (Exception e) {
            // 로깅 추가
            e.printStackTrace();
            return false;
        }
    }
}