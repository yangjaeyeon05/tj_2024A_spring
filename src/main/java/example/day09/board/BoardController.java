package example.day09.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class BoardController {

    @Autowired
    BoardService boardService;

    // 1. 게시판 등록
    @PostMapping("/board/create")
    public boolean boardCreate(String btitle , String bcontent , String bpwd){
        return boardService.boardCreate(btitle ,bcontent , bpwd);
    }   // boardCreate() end

    // 2. 게시판 전체 출력
    @GetMapping("/board/printAll")
    public ArrayList<BoardDto> boardPrintAll(){
        ArrayList<BoardDto> list = boardService.boardPrintAll();
        return list;
    }   // boardPrintAll() end

    // 3. 게시판 상세 출력
    @GetMapping("/board/print")
    public BoardDto boardPrint(int bno){
        BoardDto boardDto = boardService.boardPrint(bno);
        return boardDto;
    }   // boardPrint() end

    // 4. 게시판 수정
    @PutMapping("/board/update")
    public boolean boardUpdate(int bno , String bpwd , String bcontent){
        return boardService.boardUpdate(bno , bpwd , bcontent);
    }   // boardUpdate() end

    // 5. 게시판 삭제
    @DeleteMapping("/board/delete")
    public boolean boardDelete(int bno , String bpwd){
        return boardService.boardDelete(bno , bpwd);
    }   // boardDelete() end

    // * 비밀번호 확인

}
