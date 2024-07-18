package web.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;

@Service
public class MemberService {

    @Autowired
    MemberDao memberDao;

    // 1. 회원가입
    public boolean doSignup(MemberDto memberDto){
        System.out.println("MemberService.doSignup");
        System.out.println("memberDto = " + memberDto);
        return memberDao.doSignup(memberDto);
    }   // doSignup() end

    @Autowired  // 현제 요청을 보낸 클라이언트의 HTTP 요청정보를 가지고 있는 객체를 주입
    HttpServletRequest request;

    // 2. 로그인
    public boolean doLogin( MemberDto memberDto ){
        System.out.println("MemberController.doLogin");
        System.out.println("memberDto = " + memberDto);

        int result = memberDao.doLogin(memberDto);
        if(result >= 1) {   // 만약에 로그인 성공시
            // - 빌더패턴 : 생성자가 아닌 메소드를 이용한 방식의 객체 생성
            MemberDto loginDto = MemberDto.builder()
                    .no(result)
                    .id(memberDto.getId())
                    .build();
            // ------ HTTP 세션 처리 ------- //
            // 1. 현재 요청을 보내언 클라이언트의 세션 객체 호출
            HttpSession session = request.getSession();
            // 2. 세션객체에 속성 추가
            session.setAttribute("loginDto", loginDto);
            return true;
        }
        return false;
    }   // doLogin() end

    // 3. 로그인 상태 반환
    public MemberDto doLoginCheck(){
        // 1. 현재 요청을 보내온 클라이언트의 세션 객체 호출
        HttpSession session = request.getSession();
        // 2. 세션객체내 속성 값 호출 , 타입변환 필요하다.
        MemberDto loginDto = (MemberDto) session.getAttribute("loginDto");
        return loginDto;
    }   // doLoginCheck() end

    // 4. 로그아웃 ; 세션 초기화
    public boolean doLogout(){
        // 1. 현재 요청을 보내온 클라이언트의 세션 객체 호출 및 세션 객체 내 모든 속성 값 초기화
        request.getSession().invalidate();
        return true;
    }   // doLogout() end

    // 5. 마이페이지
    public MemberDto getMypage(){
        HttpSession session = request.getSession();
        MemberDto loginDto = (MemberDto) session.getAttribute("loginDto");
        System.out.println(loginDto.getNo());
        return memberDao.getMypage(loginDto.getNo());
    }   // getMypage() end

}   // class end

