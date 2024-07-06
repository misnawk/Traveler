package com.exampl.traveler.service;

import com.exampl.traveler.mapper.PackageMapper;
import com.exampl.traveler.vo.PackageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    @Autowired
    private PackageMapper packageMapper;

    public List<PackageVO> getAllPackages() {
        return packageMapper.getAllPackages();
    }

    public PackageVO getPackageById(String id) {
        return packageMapper.getPackageById(id);
    }
}
