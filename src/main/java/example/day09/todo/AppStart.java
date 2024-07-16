package example.day09.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// [2] Web (SpringBoot)
@SpringBootApplication  // 1. 내장톰캣(웹서버) 실행 2. restful 지원 3. 스프링MVC 지원
public class AppStart {

    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
//      [1] console
//    public static void main(String[] args) {
//        // 1. 할일 등록          주고(할일내용) 받기(결과)
//        ToDoController.getInstance().todoCreate("파이썬공부");
//        // 2. 할일 전체 출력      주고(X) 받기(할일리스트)
//        ArrayList<ToDoDto> list = ToDoController.getInstance().todoReadAll();
//        System.out.println(list);
//        // 3. 할일(상태) 수정     주고(수정할할일번호) 받기(결과)
//        ToDoController.getInstance().todoUpdate(4);
//        // 4. 할일 삭제          주고(삭제할할일번호) 받기(결과)
//        ToDoController.getInstance().todoDelete(5);
//    }   // main end

}   // class end
