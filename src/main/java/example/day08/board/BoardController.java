package example.day08.board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BoardController {

    // 1. 게시판 등록
    @PostMapping("/board/create")
    public boolean boardCreate(String btitle , String bcontent , String bpwd){
        BoardDto boardDto = new BoardDto();
        boardDto.setBtitle(btitle);
        boardDto.setBcontent(bcontent);
        boardDto.setBpwd(bpwd);

        return BoardDao.getInstance().boardCreate(boardDto);
    }   // boardCreate() end

    // 2. 게시판 전체 출력
    @GetMapping("/board/printAll")
    public ArrayList<BoardDto> boardPrintAll(){
        ArrayList<BoardDto> list = BoardDao.getInstance().boardPrintAll();
        return list;
    }   // boardPrintAll() end

    // 3. 게시판 상세 출력
    @GetMapping("/board/print")
    public BoardDto boardPrint(int bno){
        BoardDto boardDto = BoardDao.getInstance().boardPrint(bno);
        return boardDto;
    }   // boardPrint() end

    // 4. 게시판 수정
    public void boardUpdate(){

    }   // boardUpdate() end

    // 5. 게시판 삭제
    public void boardDelete(){

    }   // boardDelete() end

    // * 비밀번호 확인

}
