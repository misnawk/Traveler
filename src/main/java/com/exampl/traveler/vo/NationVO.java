package com.exampl.traveler.vo;

import lombok.Data;

import java.util.List;

@Data
public class NationVO {
    private int natNO;
    private String natName;
    private String natText;
    private String natMonth;
    private String natAirTime;
    private String exchange;
    private List<CityVO> cities;


}
