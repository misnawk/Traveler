package com.exampl.traveler.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.Date;

@Data
public class TicketVO {
    @Id
    private int tickNO;
    private String binID;
    private String tickImg;
    private String tickTitle;
    private String tickText;
    private String tickOp;
    private int tickPrice;
    private Date tickDate;
    private String tickPlace;
    private String category;
}
