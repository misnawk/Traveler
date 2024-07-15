package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.TicketVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TicketMapper {
    List<TicketVO> getAllTickets();
    TicketVO getTicketByTickNO(String tickNO);
    int createOrder(Map<String, Object> params);
    int createDiary(Map<String, Object> params);

    @Select("SELECT orderID FROM orders WHERE comNO = #{tickNO}")
    Integer getOrderIdByTickNO(String tickNO);

    @Options(useGeneratedKeys = true, keyProperty = "orderID")
    Integer getLastInsertId();
}
