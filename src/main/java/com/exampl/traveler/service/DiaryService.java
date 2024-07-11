package com.exampl.traveler.service;

import com.exampl.traveler.mapper.DiaryMapper;
import com.exampl.traveler.vo.DiaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService {

    @Autowired
    private DiaryMapper diaryMapper;

    public List<DiaryVO> show(String userId) {
        return diaryMapper.show(userId);
    }

    public void save(DiaryVO diary) {
        diaryMapper.insertDiary(diary);
    }
}
