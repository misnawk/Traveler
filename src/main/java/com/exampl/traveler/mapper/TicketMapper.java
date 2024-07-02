package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.TicketVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TicketMapper {
    List<TicketVO> getAllTickets();
}
