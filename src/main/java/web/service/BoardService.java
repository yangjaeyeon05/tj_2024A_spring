package web.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    BoardDao boardDao;
    @Autowired
    MemberService memberService;
    @Autowired
    FileService fileService;

    @Autowired // 현재 요청을 보낸 클라이언트의 HTTP 요청정보를 가지고 있는 객체를 주입
    HttpServletRequest request;

    // 1. 글 전체 출력
    public ArrayList<BoardDto> bAllPrint() {
        return boardDao.bAllPrint();
    }   // bAllPrint() end

    // 2. 글 쓰기 카테고리 불러오기
    // public ArrayList<BoardDto> getBoardCategory() {
    public List<Map<String, String>> getBoardCategory() {
        return boardDao.getBoardCategory();
    }

    // 3. 글 쓰기
    public boolean bWrite(BoardDto boardDto) {
        // 글 작성을 요청한 회원의 로그인 회원번호 구하기
        // 1. 로그인 세션에서 값 호출
        MemberDto loginDto = memberService.doLoginCheck();
        System.out.println("loginDto = " + loginDto);
        if (loginDto == null) return false;

        // 3. 속성 호출
        int loginMno = loginDto.getNo();
        // 4. boardDto에 담아주기
        boardDto.setNo(loginMno);

        // - 파일 업로드 처리
            // - 업로드된 파일 존재하면
            if(!boardDto.getUploadFile().isEmpty()){
                String uploadFileName = fileService.fileUpload(boardDto.getUploadFile());
                // 1. 만약에 업로드가 실패했으면 글쓰기 실패
                if (uploadFileName == null) return false;
                // 2. boardDto에 업로드된 파일명 넣어주기
                boardDto.setBfile(uploadFileName);
            }
        // - DB 처리
        return boardDao.bWrite(boardDto);
    }   // bWrite() end

    // 4. 상세페이지
    public BoardDto bRead(int bno){
        return boardDao.bRead(bno);
    }

    // 5. 글 삭제
    public boolean bDelete(int bno){
        // 1. 로그인 세션에서 값 호출
        MemberDto loginDto = memberService.doLoginCheck();
        System.out.println("loginDto = " + loginDto);
        if (loginDto == null) return false;
        int loginMno = loginDto.getNo();
        return boardDao.bDelete(bno , loginMno);
    }   // bDelete() end

    // 6. 글 수정
    public boolean bUpdate(Map<String, String> map){
        System.out.println("BoardService.bUpdate");
        System.out.println("map = " + map);
        // 1. 로그인 세션에서 값 호출
        MemberDto loginDto = memberService.doLoginCheck();
        System.out.println("loginDto = " + loginDto);
        if (loginDto == null) return false;
        int loginMno = loginDto.getNo();
        map.put("no", String.valueOf(loginMno));
        return boardDao.bUpdate(map);
    }   // bUpdate() end

}   // class end
