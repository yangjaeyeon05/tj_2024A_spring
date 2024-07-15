package example.day08.todo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController     // 해당 클래스가 스프링 MVC 패턴에서 controller 역할 , 스프링 컨테이너(JVM 저장소) 빈(객체) 등록
public class ToDoController {

    // (console 할때만 사용) [1] 싱글톤 패턴 : JVM 메소드 영역에 static 변수에 객체 주소값 저장
//    private static ToDoController toDoController = new ToDoController();
//    private ToDoController(){}
//    public static ToDoController getInstance(){
//        return toDoController;
//    }

    // 1. 할일 등록
    // [POST] http://localhost:8080/todo/create?tcontent=책읽기
    @PostMapping("/todo/create")
    public boolean todoCreate(String content){
        return ToDoDao.getInstance().todoCreate(content);
    }
    // 2. 할일 전체 출력
    // [GET] http://localhost:8080/todo/readall
    @GetMapping("/todo/readall")
    public ArrayList<ToDoDto> todoReadAll(){
        ArrayList<ToDoDto> result = ToDoDao.getInstance().todoReadAll();
        return result;
    }
    // 3. 할일 (상태 )수정
    // [UPDATE] http://localhost:8080/todo/update?tno=3
    @PutMapping("/todo/update")
    public boolean todoUpdate(int tno){
        boolean result = ToDoDao.getInstance().todoUpdate(tno);
        return result;
    }
    // 4. 할일 삭제
    // [DELETE] http://localhost:8080/todo/delete?tno=2
    @DeleteMapping("/todo/delete")
    public boolean todoDelete(int tno){
        return ToDoDao.getInstance().todoDelete(tno);
    }
}   // class end
