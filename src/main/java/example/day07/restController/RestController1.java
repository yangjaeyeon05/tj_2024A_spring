package example.day07.restController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

// including RESTful : HTTP기반의 지원을 제공하는 인터페이스 구축
    // SPRING WEB 아닌 환경 = servlet 클래스 직접 구현 , servlet 클래스를 controller 사용
    // SPRING WEB 환경 = MVC2 3티어 제공하여 controller 자동으로 서블릿 등록

// 해당 클래스가 SPRING MVC 에서 controller 역할의 클래스임을 등록 , 스프링 컨테이너(전역 저장소) 빈(객체) 등록
    // - 제어 역전 (IOC) : 객체 관리를 개발자가 아닌 스프링이 해준다.
    // 왜?) 여러 개발자가 공통으로 사용한 객체는 1명이 관리하면 좋은데 그 한명이 관리를 스프링이 대신 해줌.
@Controller
public class RestController1 {

    //  @RequestMapping(value = "해당메소드랑매핑할HTTP주소" , method = RequestMethod.(HTTP메소드))
        // value : "(http://localhost:8080)ip와 port 생략/example/rest1"
            // - 서버내 동일한 URL를 정의할 수 없다. 하지만 HTTP 메소드가 다를 경우 동일한 주소 정의 가능하다.
        // method : RequestMethod.HTTP메소드명  : GET,POST,PUT,DELETE

    // [1] HTTP GET // http://localhost:8080/example/rest1
    @RequestMapping(value = "/example/rest1" , method = RequestMethod.GET)
    public void getRest(HttpServletRequest req , HttpServletResponse resp) throws IOException {
        System.out.println("RestController1.getRest");
        // 데이터 요청
        String key = req.getParameter("key");
        System.out.println("key = " + key);
        // 데이터 응답
        resp.getWriter().print("[GET]ClientHi");
    }
    // [2] HTTP POST    // http://localhost:8080/example/rest1
    @RequestMapping(value = "/example/rest1" , method = RequestMethod.POST)
    public void postRest(HttpServletRequest req , HttpServletResponse resp) throws IOException { // 일반 자바 메소드 위에
        System.out.println("RestController1.postRest");
        // 데이터 요청
        String key = req.getParameter("key");
        System.out.println("key = " + key);
        // 데이터 응답
        resp.getWriter().print("[POST]ClientHi");
    }
    // [3] HTTP PUT
    @RequestMapping(value = "/example/rest1" , method = RequestMethod.PUT)
    public void putRest(HttpServletRequest req , HttpServletResponse resp) throws IOException {
        System.out.println("RestController1.putRest");
        // 데이터 요청
        String key = req.getParameter("key");
        System.out.println("key = " + key);
        // 데이터 응답
        resp.getWriter().print("[PUT]ClientHi");
    }
    // [4] HTTP DELETE
    @RequestMapping(value = "/example/rest1" , method = RequestMethod.DELETE)
    public void deleteRest(HttpServletRequest req , HttpServletResponse resp) throws IOException {
        System.out.println("RestController1.deleteRest");
        // 데이터 요청
        String key = req.getParameter("key");
        System.out.println("key = " + key);
        // 데이터 응답
        resp.getWriter().print("[DELETE]ClientHi");
    }


}   // class end
