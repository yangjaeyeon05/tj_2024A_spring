package example.day07.todo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/todo")
public class ToDoController {

    @GetMapping("/print")
    public ArrayList<ToDoDto> print(HttpServletRequest request){
        ArrayList<ToDoDto> list = ToDoDao.getInstance().print();
        System.out.println("---------get컨트롤 통신 성공---------");
        return list;
    }
    @PostMapping("/save")
    public boolean addContent(HttpServletRequest request){
        System.out.println("---------post컨트롤 통신 성공---------");
        ToDoDto toDoDto = new ToDoDto();
        String key = request.getParameter("key");
        System.out.println("key = " + key);
        toDoDto.setContent(key);
        return ToDoDao.getInstance().addContent(toDoDto);
    }
    @PutMapping("/update")
    public  boolean updateContent(HttpServletRequest request){
        System.out.println("---------put컨트롤 통신 성공---------");
        int tno = Integer.parseInt(request.getParameter("key"));
        ToDoDto result = ToDoDao.getInstance().getToDoDto( tno );
        if( result != null ){
            result.setState( result.getState() == 0 ? 1 : 0 );
            boolean result2 = ToDoDao.getInstance().update( result );
            return result2;
        }
        return false;
    }
    @DeleteMapping("/delete")
    public  boolean deleteContent(HttpServletRequest request){
        System.out.println("---------delete컨트롤 통신 성공---------");
        int tno = Integer.parseInt(request.getParameter("key"));
        ToDoDto dto = new ToDoDto();
        dto.setTno(tno);
        return ToDoDao.getInstance().deleteContent(dto);
    }
}
