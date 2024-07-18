package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// == AJAX 통신용이 아닌 템플릿 반환하는 컨트롤러 == //
// @RestController // @Controller + @ResponseBody(응답 JSON객체)
@Controller // JSON객체가 아닌 템플릿 파일 반환 하므로 @ResponseBody 없이 사용
public class ViewController {
    // ============= [1] 레이아웃 ============= //
    @GetMapping("/")    // http://localhost:8080/ // 페이지 요청은 HTTP의 GET방식을 주로 사용한다.
    public String index(){
        return "/index.html";   // templates 폴더내 반환할 경로와 파일명
    }
    // ============= [2] 회원관련 ============ //
    @GetMapping("/member/signup")
    public String mSignup(){
        return "/member/signup.html";
    }
    @GetMapping("/member/login")
    public String mLogin(){
        return "/member/login.html";
    }
    @GetMapping("/member/findid")
    public String mFindId(){
        return "/member/findid.html";
    }
    @GetMapping("/member/findpw")
    public String mFindPw(){
        return "/member/findpw.html";
    }
    @GetMapping("/member/mypage")
    public String myinfo(){
        return "/member/myinfo.html";
    }

}   // class end
