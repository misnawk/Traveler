package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.TicketVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface TicketMapper {
    List<TicketVO> getAllTickets();
    TicketVO getTicketByTickNO(String tickNO);
    int createOrder(Map<String, Object> params);
    void createDiary(Map<String, Object> params);
}