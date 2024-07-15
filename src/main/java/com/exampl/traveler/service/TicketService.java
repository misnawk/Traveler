package com.exampl.traveler.service;

import com.exampl.traveler.mapper.TicketMapper;
import com.exampl.traveler.vo.TicketVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TicketService {
    @Autowired
    private final TicketMapper ticketMapper;

    @Transactional
    public Integer createOrder(String userId, String tickNO, int quantity, Date useDate) {
        Map<String, Object> orderParams = new HashMap<>();
        orderParams.put("userId", userId);
        orderParams.put("comNO", tickNO);
        orderParams.put("binCate", "3");
        orderParams.put("totalCnt", quantity);
        orderParams.put("orderDate", new Date());
        orderParams.put("useDate", useDate);

        ticketMapper.createOrder(orderParams);

        return ticketMapper.getLastInsertId();
    }

    @Transactional
    public void createDiary(String userId, int orderId, Date useDate, String diaryTitle) {
        Map<String, Object> diaryParams = new HashMap<>();
        diaryParams.put("userId", userId);
        diaryParams.put("orderId", orderId);
        diaryParams.put("allDay", useDate);
        diaryParams.put("useDate", useDate);
        diaryParams.put("diaryTitle", diaryTitle);

        ticketMapper.createDiary(diaryParams);
    }


    public TicketVO getTicketByTickNO(String tickNO) {
        return ticketMapper.getTicketByTickNO(tickNO);
    }

    public List<TicketVO> getAllTickets() {
        return ticketMapper.getAllTickets();
    }

    public Integer getOrderIdByTickNO(String tickNO) {
        return ticketMapper.getOrderIdByTickNO(tickNO);
    }
}
