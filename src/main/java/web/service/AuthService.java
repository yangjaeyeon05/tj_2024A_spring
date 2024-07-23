package web.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Random;

@Service    // 스프링 컨테이너에 등록하고 빈(객체) 생성
public class AuthService {

    // 1. 인증번호 요청/생성
    public boolean doAuth(){
        // 1. 인증코드가 문자인 이유 : 앞자리에 0이 들어가야해서
        String authCode = "012345";
        System.out.println("authCode = " + authCode);
        /*for(int i = 0; i < 6; i++){
            authCode += (new Random().nextInt(5));
        }*/
        return true;
    }   // doAuth() end

    public boolean doAuthCode(String authCodeInput){
        /*if(authCodeInput =){
            return true;
        }else {
            return false;
        }*/
        return false;
    }   // doAuthCode() end

}   // class end
