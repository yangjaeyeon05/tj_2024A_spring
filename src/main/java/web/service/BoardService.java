package web.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;

import java.util.ArrayList;

@Service
public class BoardService {
    @Autowired
    BoardDao boardDao;
    @Autowired
    MemberService memberService;

    @Autowired // 현재 요청을 보낸 클라이언트의 HTTP 요청정보를 가지고 있는 객체를 주입
    HttpServletRequest request;

    // 1. 글 전체 출력
    public ArrayList<BoardDto> bAllPrint(){
        return boardDao.bAllPrint();
    }   // bAllPrint() end

    // 2. 글 쓰기 카테고리 불러오기
    public ArrayList<BoardDto> getBoardCategory() {
        return boardDao.getBoardCategory();
    }

    // 3. 글 쓰기
    public boolean bWrite(BoardDto boardDto){
        MemberDto loginDto=memberService.doLoginCheck();
        int loginMno=loginDto.getNo();
        if (loginDto == null){
            return false;
        } else {return boardDao.bWrite(boardDto,loginMno);}
    }

    // 4. 상세페이지
    @GetMapping("/read")
    public BoardDto bRead(int bno){
        return boardDao.bRead(bno);
    }

    // 로그인 체크
/*    public MemberDto mLoginCheck() {
        // 1. 요청을 보낸 클라이언트의 세션 객체 호출
        HttpSession session = request.getSession();
        // 2. 세션 객체내 속성 값 호출, 타입 변환 필요(모두 Object로 저장되어 있다)
        MemberDto loginDto = (MemberDto) session.getAttribute("loginDto");
        return loginDto;
    }*/
}   // class end
