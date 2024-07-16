package example.day09.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service    // 서비스 역할 , 스프링 컨테이너에 빈/객체 등록
public class ToDoService {

    @Autowired
    ToDoDao toDoDao;

    // 1. 할일 등록
    public boolean todoCreate(String content){
        return toDoDao.todoCreate(content);
    }
    // 2. 할일 전체 출력
    public ArrayList<ToDoDto> todoReadAll(){
        ArrayList<ToDoDto> result = toDoDao.todoReadAll();
        return result;
    }
    // 3. 할일 (상태 )수정
    public boolean todoUpdate(int tno){
        boolean result = toDoDao.todoUpdate(tno);
        return result;
    }
    // 4. 할일 삭제
    public boolean todoDelete(int tno){
        return toDoDao.todoDelete(tno);
    }
}
