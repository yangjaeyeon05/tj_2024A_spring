package example.day07.restController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 1. 내장 톰캣(웹서버) 실행
// 2. 동일 패키지 또는 하위 패키지들의 MVC 어노테이션(@Controller)들을 스캔해서 빈 등록 (상위페키지는 스캔불가)
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}

