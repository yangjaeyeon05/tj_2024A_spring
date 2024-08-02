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
    public BoardPageDto bAllPrint(BoardPageDto PageDto){
        // --- 매개변수
        // 1. page : 페이징처리에서 사용할 현재 페이지 번호
        // 2. bcno : 현재 선택된 카테고리(부모/상위) 번호
        // 3. searchKey : 검색 조회 시 사용되는 필드명
        // 4. searchKeyword : 검색 조회 시 사용되는 필드의 값
        System.out.println("BoardController.bAllPrint");
        System.out.println("PageDto = " + PageDto);
        return boardService.bAllPrint(PageDto);
    }   // bAllPrint() end

    // 2. 글 쓰기 카테고리 불러오기
    @GetMapping("/getcategory")
    // public ArrayList<BoardDto> getBoardCategory(){
    public List<Map<String , String>> getBoardCategory(){
        System.out.println("BoardController.getBoardCategory");
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

    // 7. 게시물의 댓글 쓰기 (기능) 처리
    // ?? 왜 post 사용하는지?  // http://localhost:8080/board/reply/write // 관례적으로 글을 쓰는 행위를 하는 경우 postmapping을 쓴다.
    @PostMapping("/reply/write")
    public boolean bReplyWrite(@RequestBody Map<String , String> map){
        // ?? 왜 @RequestBody 사용하는지 ??
        // JS에서 객체를 jason문자열 타입으로 보내줬기 때문에 그 타입을 java 타입으로 변환하기 위해서
        System.out.println("BoardController.bReplyWrite");
        System.out.println("map = " + map);
        // 왜? map을 사용하는 경우 특정 상황 기준에 자료들을 정리해 두면 검색 , 정렬 , 통계할 때 유용한데 이번 수업의 경우 dto 대신 map을 쓰는 연습을 하기 위해서
        return boardService.bReplyWrite(map);
    }   // bReplyWrite() end

    // 8. 댓글출력
    @GetMapping("/reply/read")
    public  List<Map<String , String>> bReplyRead(int bno){
        System.out.println("BoardController.bReplyRead");
        System.out.println("bno = " + bno);
        return boardService.bReplyRead(bno);
    }   // bReplyRead() end

}   // class end
