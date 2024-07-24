package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.BoardDto;
import web.service.BoardService;

import java.util.ArrayList;

@RequestMapping("/board")
@RestController
public class BoardController {
    @Autowired
    BoardService boardService;

    // 1. 글 전체 출력
    @GetMapping("/all")
    public ArrayList<BoardDto> bAllPrint(){
        return boardService.bAllPrint();
    }   // bAllPrint() end

    // 2. 글 쓰기 카테고리 불러오기
    @GetMapping("/getcategory")
    public ArrayList<BoardDto> getBoardCategory(){
        return boardService.getBoardCategory();
    }

    // 3. 글 쓰기
    @PostMapping("/write")
    public boolean bWrite(@RequestBody BoardDto boardDto){
        return boardService.bWrite(boardDto);
    }

    // 4. 상세페이지
    @GetMapping("/read")
    public BoardDto bRead(int bno){
        return boardService.bRead(bno);
    }



}   // class end
