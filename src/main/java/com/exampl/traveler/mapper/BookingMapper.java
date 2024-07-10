package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.BookingVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookingMapper {
    int insertBooking(BookingVO booking);

}
