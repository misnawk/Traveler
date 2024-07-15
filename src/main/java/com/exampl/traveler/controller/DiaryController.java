package com.exampl.traveler.controller;

import com.exampl.traveler.service.DiaryService;
import com.exampl.traveler.vo.DiaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/diary")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @GetMapping("/{userId}")
    public String getDiary(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("userId", userId);
        return "schedule/diary";  // This will render diary.html
    }

    @GetMapping("/entries/{userId}")
    @ResponseBody
    public List<DiaryVO> getDiaryEntries(@PathVariable("userId") String userId) {
        return diaryService.show(userId);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> saveDiaryEntry(@RequestBody DiaryVO diaryVO) {
        try {
            System.out.println("Received diary entry: " + diaryVO);  // 로깅 추가
            diaryService.save(diaryVO);
            return ResponseEntity.ok("일정이 저장되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();  // 스택 트레이스 출력
            return ResponseEntity.badRequest().body("일정 저장 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
    @PutMapping("/{diaryNO}")
    @ResponseBody
    public ResponseEntity<String> updateDiaryEntry(@PathVariable("diaryNO") Integer diaryNO, @RequestBody DiaryVO diaryVO) {
        try {
            diaryVO.setDiaryNO(diaryNO);
            diaryService.update(diaryVO);
            return ResponseEntity.ok("일정이 수정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("일정 수정 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{diaryNO}")
    @ResponseBody
    public ResponseEntity<String> deleteDiaryEntry(@PathVariable("diaryNO") String diaryNO) {
        System.out.println(diaryNO);
        try {
            diaryService.delete(diaryNO);
            return ResponseEntity.ok("일정이 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("일정 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
