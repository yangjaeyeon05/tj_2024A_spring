package example.day07.todo;

import jakarta.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ToDoDao {
    private static ToDoDao toDoDao = new ToDoDao();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    private ToDoDao (){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist" , "root" , "1234");
        }catch (Exception e){
            System.out.println(">> 연동 실패"+e);
        }   // try end
    }
    public static ToDoDao getInstance(){
        return toDoDao;
    }
    // 내용 프린트
    public ArrayList<ToDoDto> print(){
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
    }
    // 내용 저장
    public boolean addContent(ToDoDto toDoDto){
        try{
            String sql = "insert into todo(content) values(?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,toDoDto.getContent());
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    // 내용 업데이트 위한 리스트 가져오기
    public ToDoDto getToDoDto( int tno){
        try {
            String  sql="select * from todo where tno=?";
            ps=conn.prepareStatement(sql);//실행
            ps.setInt(1,tno); //번호찾기
            rs=ps.executeQuery(); //전체출력
            if (rs.next()){ //가져온값이 있으면
                ToDoDto dto = new ToDoDto();
                dto.setTno( rs.getInt(1));
                dto.setContent( rs.getString(2));
                dto.setState( rs.getInt(3));
                return dto;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return  null;
    }
    // 내용 수정
    public boolean update(ToDoDto toDoDto){
        try {
                String sql="update todo set state= ? where tno=? ";
                ps= conn.prepareStatement(sql);
                ps.setInt(1,toDoDto.getState());
                ps.setInt(2,toDoDto.getTno());
                ps.executeUpdate();
                return true;

        }catch (Exception e){
            System.out.println(e);
        }
        return  false;
    }
    // 내용 삭제
    public  boolean deleteContent(ToDoDto toDoDto){
        try {
            String sql="delete from todo where tno = ? ";
            ps= conn.prepareStatement(sql);
            ps.setInt(1,toDoDto.getTno());
            ps.executeUpdate();
            return true; // 삭제성공

        }catch (Exception e){
            System.out.println(e);
        }
        return  false; // 실패
    }
}   // class end
