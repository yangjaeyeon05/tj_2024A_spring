package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.BoardDto;
import web.model.dto.BoardPageDto;
import web.service.BoardService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/board")
@RestController
public class BoardController {
    @Autowired
    BoardService boardService;

    // 1. 글 전체 출력
    @GetMapping("/all")
    public BoardPageDto bAllPrint(
            BoardPageDto PageDto
            // 1. page : 페이징처리에서 사용할 현재 페이지 번호
            // 2. bcno : 현재 선택된 카테고리(부모/상위) 번호
    ){
        System.out.println("BoardController.bAllPrint");
        System.out.println("PageDto = " + PageDto);
        return boardService.bAllPrint(PageDto);
    }   // bAllPrint() end

    // 2. 글 쓰기 카테고리 불러오기
    @GetMapping("/getcategory")
    // public ArrayList<BoardDto> getBoardCategory(){
    public List<Map<String , String>> getBoardCategory(){
        return boardService.getBoardCategory();
    }

    // 3. 글 쓰기
    @PostMapping("/write")
    public boolean bWrite(BoardDto boardDto){
        System.out.println("BoardController.bWrite");
        System.out.println("boardDto = " + boardDto);
        return boardService.bWrite(boardDto);
    }

    // 4. 상세페이지
    @GetMapping("/read")
    public BoardDto bRead(int bno){
        return boardService.bRead(bno);
    }

    // 5. 글 삭제
    @DeleteMapping("/delete")
    public boolean bDelete(int bno){
        return boardService.bDelete(bno);
    }   // bDelete() end

    // 6. 글 수정
    @PutMapping("/edit")
    public boolean bUpdate(@RequestBody Map<String, String> map){
        System.out.println("BoardController.bUpdate");
        System.out.println("map = " + map);
        return boardService.bUpdate(map);
    }   // bUpdate() end

}   // class end
