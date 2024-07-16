package example.day08.board;

import example.day07.todo.ToDoDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDao {
    private static BoardDao boardDao = new BoardDao();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    private BoardDao (){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/board" , "root" , "1234");
        }catch (Exception e){
            System.out.println(">> 연동 실패"+e);
        }   // try end
    }
    public static BoardDao getInstance(){
        return boardDao;
    }

    // 1. 게시판 등록
    public boolean boardCreate(BoardDto boardDto){
        try{
            String sql = "insert into boardlist(btitle , bcontent , bpwd) values (? , ? , ? )";
            ps = conn.prepareStatement(sql);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2,boardDto.getBcontent());
            ps.setString(3,boardDto.getBpwd());
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // boardCreate() end

    // 2. 게시판 전체 출력
    public ArrayList<BoardDto> boardPrintAll(){
        ArrayList<BoardDto> list = new ArrayList<>();
        try{
            String sql = "select * from boardlist";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                String btitle = rs.getString("btitle");
                String bcontnet = rs.getString("bcontent");
                String bdate = rs.getString("bdate");
                int bview = rs.getInt("bview");
                BoardDto boardDto = new BoardDto();
                boardDto.setBtitle(btitle);
                boardDto.setBcontent(bcontnet);
                boardDto.setBdate(bdate);
                boardDto.setBview(bview);
                list.add(boardDto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }   // boardPrintAll() end

    // 3. 게시판 상세 출력
    public BoardDto boardPrint(int bno){
        BoardDto boardDto = new BoardDto();
        try{

        }catch (Exception e){
            System.out.println(e);
        }
        return boardDto;
    }   // boardPrint() end

    // 4. 게시판 수정
    public void boardUpdate(){

    }   // boardUpdate() end

    // 5. 게시판 삭제
    public void boardDelete(){

    }   // boardDelete() end

    // * 비밀번호 확인
    public void confirmPwd(){

    }
}
