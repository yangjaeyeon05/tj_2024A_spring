package example.day09.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController     // 해당 클래스가 스프링 MVC 패턴에서 controller 역할 , 스프링 컨테이너(JVM 저장소) 빈(객체) 등록
public class ToDoController {

    @Autowired
    ToDoService toDoService;

    // 1. 할일 등록
    @PostMapping("/todo/create")
    public boolean todoCreate(String content){
        return toDoService.todoCreate(content);
    }
    // 2. 할일 전체 출력
    @GetMapping("/todo/readall")
    public ArrayList<ToDoDto> todoReadAll(){
        ArrayList<ToDoDto> result = toDoService.todoReadAll();
        return result;
    }
    // 3. 할일 (상태 )수정
    @PutMapping("/todo/update")
    public boolean todoUpdate(int tno){
        boolean result = toDoService.todoUpdate(tno);
        return result;
    }
    // 4. 할일 삭제
    @DeleteMapping("/todo/delete")
    public boolean todoDelete(int tno){
        return toDoService.todoDelete(tno);
    }

}   // class end
