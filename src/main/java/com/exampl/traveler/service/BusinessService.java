package com.exampl.traveler.service;

import com.exampl.traveler.mapper.BusinessMapper;
import com.exampl.traveler.vo.BusinessVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessService {
    private final BusinessMapper businessMapper;

    public void saveBusiness(BusinessVO businessVO) {
        businessMapper.binIdInsert(businessVO);
    }
}