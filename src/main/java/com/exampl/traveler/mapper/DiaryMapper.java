package com.exampl.traveler.mapper;

import com.exampl.traveler.vo.DiaryVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DiaryMapper {
    List<DiaryVO> show(String userId);
    void insertDiaryEntry(DiaryVO diaryVO);
    void updateDiaryEntry(DiaryVO diaryVO);
    void deleteDiaryEntry(String diaryNO);


}
