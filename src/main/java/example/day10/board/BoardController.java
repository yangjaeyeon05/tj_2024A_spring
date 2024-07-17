package example.day10.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class BoardController {

    @Autowired
    BoardService boardService;

    // 1. 게시판 등록
    @PostMapping("/boardcopy/create")
    public boolean boardCreate(String btitle , String bcontent , String bpwd){
        return boardService.boardCreate(btitle ,bcontent , bpwd);
    }   // boardCreate() end

    // 2. 게시판 전체 출력
    @GetMapping("/boardcopy/printAll")
    public ArrayList<BoardDto> boardPrintAll(){
        ArrayList<BoardDto> list = boardService.boardPrintAll();
        return list;
    }   // boardPrintAll() end

    // 3. 게시판 상세 출력
    @GetMapping("/boardcopy/print")
    public BoardDto boardPrint(int bno){
        BoardDto boardDto = boardService.boardPrint(bno);
        return boardDto;
    }   // boardPrint() end

    // 4. 게시판 수정
    @PutMapping("/boardcopy/update")
    public boolean boardUpdate(int bno , String bcontent){
        return boardService.boardUpdate(bno , bcontent);
    }   // boardUpdate() end

    // 5. 게시판 삭제
    @DeleteMapping("/boardcopy/delete")
    public boolean boardDelete(int bno){
        return boardService.boardDelete(bno);
    }   // boardDelete() end

    // * 비밀번호 확인
    @PutMapping("/boardcopy/confirm")
    public boolean confirmPwd(int bno , String bpwd){
        return boardService.confirmPwd(bno , bpwd);
    }


}
