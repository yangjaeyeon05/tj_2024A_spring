package web.model.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import web.model.dto.BoardDto;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BoardDao extends Dao{

    // 1. 글 전체 출력
    public ArrayList<BoardDto> bAllPrint(){
        ArrayList<BoardDto> list = new ArrayList<>();
        try{
            String sql = "select *from board inner join member where board.no = member.no";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                BoardDto boardDto = BoardDto.builder()
                        .bno(rs.getInt("bno"))
                        .btitle(rs.getString("btitle"))
                        .id(rs.getString("id"))
                        .bdate(rs.getString("bdate"))
                        .bview(rs.getInt("bview"))
                        .build();
                list.add(boardDto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }   // bAllPrint() end

    // 2. 카테고리 출력
   //  public ArrayList<BoardDto> getBoardCategory() {
    public List<Map<String , String>> getBoardCategory(){
        System.out.println("BoardDao.getBoardCategory");
        // -  List 컬렉션 map 컬렉션을 여러개 선언 하기 위해 list 선언
        List<Map<String, String>> list = new ArrayList<>();
        try{
            String sql = "select * from bcategory";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                // - map 컬렉션 선언
                Map<String , String> map = new HashMap<>();
                // - map 컬렉션 엔트리 2개 추가 카테고리 넘버 , 이름
                map.put("bcno" , String.valueOf(rs.getInt("bcno")));
                map.put("bcname" , String.valueOf(rs.getString("bcname")));
                list.add(map);
                System.out.println(list);
            }
        }catch (Exception e){
            System.out.println("getBoardCategory()" + e);
        }
        return list;    // 리스트 반환
    }   // getBoardCategory() end

    // 3. 글 쓰기
    public boolean bWrite(BoardDto boardDto){
        try {
            String sql="insert into board(btitle,bcontent,no,bcno,bfile) values(?,?,?,?,?);";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2,boardDto.getBcontent());
            ps.setLong(3,boardDto.getNo());
            ps.setLong(4,boardDto.getBcno());
            ps.setString(5, boardDto.getBfile());
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
            return true;
        }catch (Exception e){System.out.println("e = " + e);}
        return false;
    }   // bWrite() end

    // 4. 상세페이지
    @GetMapping("/read")
    public BoardDto bRead(int bno){
        try{
            String sql = "select * from board inner join member on board.no = member.no inner join bcategory on board.bcno = bcategory.bcno where bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                BoardDto boardDto = BoardDto.builder()
                        .bcname(rs.getString("bcname"))
                        .bno(rs.getInt("bno"))
                        .btitle(rs.getString("btitle"))
                        .bcontent(rs.getString("bcontent"))
                        .id(rs.getString("id"))
                        .bdate(rs.getString("bdate"))
                        .bview(rs.getInt("bview"))
                        .build();
                return boardDto;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }   // bRead() end

    // 5. 글 삭제
    public boolean bDelete(int bno , int loginNo){
        try{
            String sql = "delete from board where bno = ? and no = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            ps.setInt(2,loginNo);
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // bDelete() end

    // 6. 글 수정
    public boolean bUpdate(Map<String, String> map){
        System.out.println("BoardDao.bUpdate");
        System.out.println("map = " + map);
        try{
            String sql = "update board set btitle = ? , bcontent = ? , bcno = ? where bno = ? and no = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1 , map.get("btitle"));
            ps.setString(2 , map.get("bcontent"));
            ps.setLong(3,Integer.parseInt(map.get("bcno")));
            ps.setInt(4,Integer.parseInt(map.get("bno")));
            ps.setInt(5,Integer.parseInt(map.get("no")));
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // bUpdate() end

}   // class end
