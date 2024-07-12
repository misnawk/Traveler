package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.TicketVO;
import com.exampl.traveler.vo.UserOrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface TicketMapper {
    List<TicketVO> getAllTickets();
    TicketVO getTicketByTickNO(@Param("tickNO") String tickNO);

    void insertOrder(UserOrderVO userOrderVO);
    void insertDiary(@Param("userId") String userId, @Param("orderID") int orderID,
                     @Param("allday") Date allday, @Param("diaryTitle") String diaryTitle);
}