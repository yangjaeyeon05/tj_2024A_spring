package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.service.AuthService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/auth")    // 해당 클래스내 메소드들의 공통 HTML URL
public class AuthController {

    @Autowired
    AuthService authService;

    // 1. 인증번호 요청/생성
    @GetMapping("/code")
    public boolean doAuth(String email){
        return authService.doAuth(email);
    }   // doAuth() end

    // 2. 입력받은 값과 인증번호를 비교
    @PostMapping("/check")
    public boolean doAuthCode(String authCodeInput){
        return authService.doAuthCode(authCodeInput);
    }   // doAuthCode() end

}   // class end
