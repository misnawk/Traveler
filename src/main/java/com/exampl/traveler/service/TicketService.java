package com.exampl.traveler.service;

import com.exampl.traveler.mapper.TicketMapper;
import com.exampl.traveler.vo.TicketVO;
import com.exampl.traveler.vo.UserOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    private static final Logger log = LoggerFactory.getLogger(TicketService.class);

    private final TicketMapper ticketMapper;

    @Autowired
    public TicketService(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public List<TicketVO> getAllTickets() {
        return ticketMapper.getAllTickets();
    }

    public TicketVO getTicketByTickNO(String tickNO) {
        return ticketMapper.getTicketByTickNO(tickNO);
    }

    @Transactional
    public void insertOrder(UserOrderVO userOrderVO) {
        log.info("insertOrder 메서드 시작");
        log.info("Received userOrderVO: {}", userOrderVO);
        userOrderVO.setBinCate("3");
        userOrderVO.setOrderDate(new Date());
        ticketMapper.insertOrder(userOrderVO);
        log.info("Order inserted, getting ticket info");
        TicketVO ticket = ticketMapper.getTicketByTickNO(userOrderVO.getComNO());
        if (ticket == null) {
            log.error("Ticket not found for comNO: {}", userOrderVO.getComNO());
            throw new RuntimeException("Ticket not found");
        }
        log.info("Inserting diary entry");
        ticketMapper.insertDiary(userOrderVO.getUserId(), userOrderVO.getOrderId(),
                ticket.getTickDate(), ticket.getTickTitle());
        log.info("Diary entry inserted");
    }
}
