package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.TicketVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface TicketMapper {
    List<TicketVO> getAllTickets();
    TicketVO getTicketByBinID(String binID);
    int reserveTicket(@Param("userId") String userId, @Param("binID") String binID);
}