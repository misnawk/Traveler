package com.exampl.traveler.vo;

import lombok.Data;
import java.util.Date;

@Data
public class PackageVO {
    private String packageNO;
    private String binId;
    private String packageTitle;
    private String packageText;
    private int packageMax;
    private int packagePrice;
    private int peopleCnt;
    private Date startDateTime;
    private Date endDateTime;
    private String imageUrl;
}
