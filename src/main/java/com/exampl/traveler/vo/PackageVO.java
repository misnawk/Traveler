package com.exampl.traveler.vo;

import lombok.Data;
import java.util.Date;

@Data
public class PackageVO {
    private String binId;
    private String packageNo;
    private String packageTitle;
    private String packageText;
    private int packageMax;
    private int packagePrice;
    private Integer peopleCnt;
    private Date startDateTime;
    private Date endDateTime;
    private String imageUrl;
}
