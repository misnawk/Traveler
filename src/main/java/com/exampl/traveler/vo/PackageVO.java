package com.exampl.traveler.vo;

import lombok.Data;
import java.util.Date;

@Data
public class PackageVO {
    private String packageNO;        // 패키지 번호
    private String binId;            // 비즈니스 ID
    private String packageTitle;     // 패키지 이름
    private String packageText;      // 패키지 내용
    private int packageMax;          // 총 인원
    private int packagePrice;        // 가격
    private Integer peopleCnt;       // 인원수
    private Date startDateTime;      // 출발 날짜 시간
    private Date endDateTime;        // 도착 날짜 시간
    private String imageUrl;         // 사진 URL
    private String packageCountry;   // 패키지 나라
}