package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.MemberDto;
import web.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    // POST http://localhost:8080/member/signup?id=qwe&pw=asd&name=강호동&email=qwe@qwe&phone=123-123-123
    @PostMapping("signup")
    // public boolean doSignup(String id , String pw , String name , String email , String phone){
    public boolean doSignup( MemberDto memberDto ){
        System.out.println("MemberController.doSignup");
        System.out.println("memberDto = " + memberDto);
        return memberService.doSignup(memberDto);
    }   // doSignup() end

}   // class end
