package example.day08.board;

import org.springframework.web.bind.annotation.*;

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
        // 해당 게시물 조회 수 처리
        BoardDao.getInstance().viewIncrease(bno);
        BoardDto boardDto = BoardDao.getInstance().boardPrint(bno);
        return boardDto;
    }   // boardPrint() end

    // 4. 게시판 수정
    @PutMapping("/board/update")
    public boolean boardUpdate(int bno , String bpwd , String bcontent){
        boolean confirmPwd = BoardDao.getInstance().confirmPwd(bno , bpwd);
        boolean result = true;
        if(confirmPwd){
            result = BoardDao.getInstance().boardUpdate(bno , bcontent);
        }else {
            return false;
        }
        return result;
    }   // boardUpdate() end

    // 5. 게시판 삭제
    @DeleteMapping("/board/delete")
    public boolean boardDelete(int bno , String bpwd){
        boolean confirmPwd = BoardDao.getInstance().confirmPwd(bno , bpwd);
        boolean result = true;
        if(confirmPwd){
            result = BoardDao.getInstance().boardDelete(bno);
        }else {
            return false;
        }
        return result;
    }   // boardDelete() end

    // * 비밀번호 확인

}
