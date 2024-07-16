package example.day09.todo;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component      // 스프링 컨테이너에 빈/객체 등록
public class ToDoDao {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    // DB 연동
    private ToDoDao (){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist" , "root" , "1234");
        }catch (Exception e){
            System.out.println(">> 연동 실패"+e);
        }   // try end
    }

    // 1. 할일 등록
    public boolean todoCreate(String content){
        try{
            String sql = "insert into todo(content) values(?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,content);
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // todoCreate() end
    // 2. 할일 전체 출력
    public ArrayList<ToDoDto> todoReadAll(){
        ArrayList<ToDoDto> list = new ArrayList<>();
        try{
            String sql = "select * from todo";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int tno = rs.getInt("tno");
                String content = rs.getString("content");
                int state = rs.getInt("state");
                ToDoDto toDoDto = new ToDoDto(tno , content , state);
                list.add(toDoDto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }   // todoReadAll() end
    // 3. 할일 (상태 )수정
    public boolean todoUpdate(int tno){
        try {
            // 수정 전 상태 조회
            String sql2 = "select * from todo where tno=?";
            ps = conn.prepareStatement(sql2);
            ps.setInt(1,tno);
            rs = ps.executeQuery();
            if(rs.next()){
                // 기존상태
                int state = rs.getInt("state");
                // 상태 변경 : 기존 상태가 0이면 1 , 1이면 0으로
                state = state == 0 ? 1 : 0;
                // 수정 코드
                String sql="update todo set state= ? where tno=? ";
                ps= conn.prepareStatement(sql);
                ps.setInt(1,state);
                ps.setInt(2,tno);
                int count = ps.executeUpdate();
                if(count == 1){
                    return true;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // todoUpdate() end
    // 4. 할일 삭제
    public  boolean todoDelete(int tno){
        try {
            String sql = "delete from todo where tno = ? ";
            ps= conn.prepareStatement(sql);
            ps.setInt(1,tno);
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false; // 실패
    }   // todoDelete() end
}   // class end
