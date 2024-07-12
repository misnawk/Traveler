package com.exampl.traveler.controller;

import com.exampl.traveler.service.BoardService;
import com.exampl.traveler.vo.BoardVO;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시판 메인 페이지 한 페이지에 5개씩 페이지 작업까지 완료
    @RequestMapping
    public String board(Model model,
                        @RequestParam(value = "tripType", required = false) Integer tripType ,
                        @RequestParam(value = "page", defaultValue = "1") int page) {
        int pageSize = 5;
        List<BoardVO> boardPage = boardService.getBoardPage(tripType,page, pageSize);
        int totalBoards = boardService.getTotalBoardCount();
        int totalPages = (int) Math.ceil((double) totalBoards / pageSize);

        model.addAttribute("board", boardPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "/board/boardList";
    }
    //게시판에서 누르면 이동되는곳
    @GetMapping("/detail")
    public String detail(@RequestParam("boardNo") int boardNo, @RequestParam("page") int page, Model model) {
        BoardVO board = boardService.selectOne(boardNo);

        model.addAttribute("board",board);
        model.addAttribute("currentPage", page);
        return "/board/boardDetail";
    }


    // 검색
    @GetMapping("/search")
    public String search(@RequestParam(value = "tripType", required = false) Integer tripType,@RequestParam("data") String data, Model model) {
        System.out.println("검색어: " + data);  // 로그로 검색어 확인
        List<BoardVO> board = boardService.search(data,tripType);

        model.addAttribute("board", board);
        model.addAttribute("currentPage", 1);
        model.addAttribute("totalPages", 1);
        return "/board/boardList";
    }


    //메인 국가 도시페이지에서 누르면 상세페이지로 가는거
    @GetMapping("/details")
    public String details(@RequestParam("boardNo") int boardNo, Model model) {
        BoardVO board = boardService.selectOne(boardNo);

        model.addAttribute("board",board);

        return "/board/boardDetail";
    }



    //글 등록 페이지
    @RequestMapping("/write")
    public String write(Model model) {
        model.addAttribute("boardVO", new BoardVO());
        return "/board/boardwrite";
    }

    //글등록
    @PostMapping("/write/save")
    public String saveBoard( @ModelAttribute BoardVO boardVO, BindingResult bindingResult, HttpSession session, Model model) {

        // 현재 날짜 설정
        boardVO.setTripDate(new Date());

        if (bindingResult.hasErrors()) {
            model.addAttribute("boardVO", boardVO);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "board/boardwrite"; // 유효성 검사 실패 시 다시 폼으로 돌아감
        }

        // 파일 업로드 처리
        MultipartFile file = boardVO.getTripImg();
        if (file != null && !file.isEmpty()) {
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            // 프로젝트의 루트 경로를 얻습니다
            String projectRoot = System.getProperty("user.dir");

            // resources/static/imgs 경로를 구성합니다
            String uploadDir = projectRoot + "/src/main/resources/static/imgs/";

            // 디렉토리가 존재하지 않으면 생성합니다
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 파일 저장 경로를 설정합니다
            File destination = new File(uploadDir + filename);

            try {
                file.transferTo(destination);
                boardVO.setTripImgPath(filename); // 파일 경로를 boardVO에 설정
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("fileUploadError", "파일 업로드 중 오류가 발생했습니다.");
                return "board/boardwrite";
            }
        }

        // 데이터베이스에 저장
        boardService.saveBoard(boardVO);

        return "redirect:/board"; // 저장 후 게시글 목록 페이지로 리디렉션
    }
}


