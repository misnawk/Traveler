package com.exampl.traveler.service;

import com.exampl.traveler.mapper.TicketMapper;
import com.exampl.traveler.vo.TicketVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public boolean reserveTicket(String userId, String tickNO, int quantity) {
        try {
            TicketVO ticket = ticketMapper.getTicketByTickNO(tickNO);
            if (ticket == null) {
                log.error("Ticket not found for tickNO: {}", tickNO);
                return false;
            }

            Map<String, Object> orderParams = new HashMap<>();
            orderParams.put("userId", userId);
            orderParams.put("comNO", tickNO);
            orderParams.put("totalcnt", quantity);

            int result = ticketMapper.createOrder(orderParams);

            if (result > 0) {
                Object orderIDObj = orderParams.get("orderID");
                long orderID;
                if (orderIDObj instanceof BigInteger) {
                    orderID = ((BigInteger) orderIDObj).longValue();
                } else if (orderIDObj instanceof Long) {
                    orderID = (Long) orderIDObj;
                } else if (orderIDObj instanceof Integer) {
                    orderID = ((Integer) orderIDObj).longValue();
                } else {
                    throw new IllegalStateException("Unexpected type for orderID: " + orderIDObj.getClass());
                }

                Map<String, Object> diaryParams = new HashMap<>();
                diaryParams.put("userId", userId);
                diaryParams.put("orderID", orderID);
                diaryParams.put("goday", ticket.getTickDate());
                diaryParams.put("diaryTitle", ticket.getTickTitle());
                diaryParams.put("time", ticket.getTickTime());  // 티켓 시간 추가

                ticketMapper.createDiary(diaryParams);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("Error while reserving ticket", e);
            return false;
        }
    }
}