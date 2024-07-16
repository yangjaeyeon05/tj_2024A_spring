package example.day09.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoardService {

    @Autowired
    BoardDao boardDao;

    // 1. 게시판 등록
    public boolean boardCreate(String btitle , String bcontent , String bpwd){
        BoardDto boardDto = new BoardDto();
        boardDto.setBtitle(btitle);
        boardDto.setBcontent(bcontent);
        boardDto.setBpwd(bpwd);

        return boardDao.boardCreate(boardDto);
    }   // boardCreate() end

    // 2. 게시판 전체 출력
    public ArrayList<BoardDto> boardPrintAll(){
        ArrayList<BoardDto> list = boardDao.boardPrintAll();
        return list;
    }   // boardPrintAll() end

    // 3. 게시판 상세 출력
    public BoardDto boardPrint(int bno){
        // 해당 게시물 조회 수 처리
        boardDao.viewIncrease(bno);
        BoardDto boardDto = boardDao.boardPrint(bno);
        return boardDto;
    }   // boardPrint() end

    // 4. 게시판 수정
    public boolean boardUpdate(int bno , String bpwd , String bcontent){
        boolean confirmPwd = boardDao.confirmPwd(bno , bpwd);
        boolean result = true;
        if(confirmPwd){
            result = boardDao.boardUpdate(bno , bcontent);
        }else {
            return false;
        }
        return result;
    }   // boardUpdate() end

    // 5. 게시판 삭제
    public boolean boardDelete(int bno , String bpwd){
        boolean confirmPwd = boardDao.confirmPwd(bno , bpwd);
        boolean result = true;
        if(confirmPwd){
            result = boardDao.boardDelete(bno);
        }else {
            return false;
        }
        return result;
    }   // boardDelete() end
}
