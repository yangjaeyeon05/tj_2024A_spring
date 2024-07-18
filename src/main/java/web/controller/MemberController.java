package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.model.dto.MemberDto;
import web.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    // POST http://localhost:8080/member/signup?id=qwe&pw=asd&name=강호동&email=qwe@qwe&phone=123-123-123
    @PostMapping("/signup")
    // public boolean doSignup(String id , String pw , String name , String email , String phone){
    public boolean doSignup( MemberDto memberDto ){
        System.out.println("MemberController.doSignup");
        System.out.println("memberDto = " + memberDto);
        return memberService.doSignup(memberDto);
    }   // doSignup() end

    @PostMapping("/login")
    public boolean doLogin( MemberDto memberDto ){
        System.out.println("MemberController.doLogin");
        System.out.println("memberDto = " + memberDto);
        return memberService.doLogin(memberDto);
    }   // doLogin() end

    // 로그인체크
    @GetMapping("/login/check")
    public MemberDto doLoginCheck(){
        return memberService.doLoginCheck();
    }   // doLoginCheck() end

    // 로그아웃
    @GetMapping("/logout")
    public boolean doLogout(){
        return memberService.doLogout();
    }   // doLogout() end

    // 마이페이지 구현
    @GetMapping("/my/info")
    public MemberDto getMypage(){
        return memberService.getMypage();
    }   // getMypage() end

}   // class end
