package web.model.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.dto.BoardDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BoardDao extends Dao{

    // 1-1 전체 게시물 수 반환 처리 , 조건추가1) 카테고리
    public int getTotalBoardSize(int bcno , String searchKey , String searchKeyword){
        try{
            String sql = "select count(*) as 총게시물수 " +
                    " from board inner join member on board.no = member.no";

            // 카테고리가 존재하면 , 0 : 카테고리가 없다는 의미 , 1 이상 : 카테고리 pk 번호
            if(bcno >= 1){
                sql += " where bcno = "+bcno;
            }
            // 검색이 존재 했을 때 , keyword가 존재하면
            if(searchKeyword.isEmpty()){    // 문자열이 비어있으면 , 검색이 없다라는 의미 뜻으로 활용

            }else { // 비어있지 않으면 , 검색이 있다라는 의미의 뜻으로 활용
                // - 카테고리가 있을때는 and 추가
                if(bcno >= 1){
                    sql += " and ";
                }else { // - 카테고리가 없을때는[전체보기] where 추가
                    sql += " where ";
                }
                // 검색 sql
                sql += searchKey + " like '%"+searchKeyword+"%' ";

            }
            System.out.println("sql = " + sql);
                // 1. 전체보기 : select count(*) as 총게시물수 from board
                // 2. 카테고리보기 : select count(*) as 총게시물수 from board where bcno = 카테고리번호

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int totalBoardSize = rs.getInt("총게시물수");
                return totalBoardSize;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }   // getBoardSize() end

    // 1. 글 전체 출력
    public ArrayList<BoardDto> bAllPrint(int startRow , int pageBoardSize , int bcno , String searchKey , String searchKeyword){
        System.out.println("BoardDao.bAllPrint");
        ArrayList<BoardDto> list = new ArrayList<>();
        try{
            String sql = "select * " +                  // 1. 조회
                    " from board inner join member " +  // 2. 조인 테이블
                    " on board.no = member.no ";        // 3. 조인 조건
            // 4. 일반조건
                // - 전체보기이면 where절 생략 , bcno = 0
                // - 카테고리별 보기이면 where절 추가 , bcno >= 1
            if(bcno >= 1){
                sql += " where bcno = " + bcno;
            }
            // 4. 일반조건2
            if(searchKeyword.isEmpty()){

            }else {
                if(bcno >= 1){
                    sql += " and ";
                }else { // - 카테고리가 없을때는[전체보기] where 추가
                    sql += " where ";
                }
                sql += searchKey + " like '%"+searchKeyword+"%'";
            }

            sql += " order by board.bno desc ";         // 5. 정렬 조건 , 내림차순
            sql += " limit ? , ?";                      // 6. 레코드 제한 , 페이징 처리

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1 , startRow);
            ps.setInt(2 , pageBoardSize);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                // 레코드를 하나씩 조회해서 Dto vs Map 컬렉션
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
    public BoardDto bRead(int bno){
        System.out.println("BoardDao.bRead");
        System.out.println("bno = " + bno);
        try{
            String sql = "select bc.bcno , bcname , bno , btitle , bcontent , id , bdate , bview , bfile " +
                    " from board b " +
                    " inner join member m " +
                    " inner join bcategory bc " +
                    " on b.no = m.no and b.bcno = bc.bcno where b.bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                BoardDto boardDto = BoardDto.builder()
                        .bcno(rs.getInt("bcno"))
                        .bcname(rs.getString("bcname"))
                        .bno(rs.getInt("bno"))
                        .btitle(rs.getString("btitle"))
                        .bcontent(rs.getString("bcontent"))
                        .id(rs.getString("id"))
                        .bdate(rs.getString("bdate"))
                        .bview(rs.getInt("bview"))
                        .bfile(rs.getString("bfile"))
                        .build();
                System.out.println(boardDto);
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

    // *. 조회 수 증가 처리
    public boolean viewIncrease(int bno){
        try{
            String sql = "update board set bview = bview + 1 where bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1 , bno);
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // viewIncrease() end

    // 7. 게시물의 댓글 쓰기 (기능) 처리
    public boolean bReplyWrite(Map<String , String> map){
        System.out.println("BoardDao.bReplyWrite");
        System.out.println("map = " + map);
        // brindex , brcontent , no , bno 왜?? 4가지를 저장하는지? DB컨셉이 디폴트 값을 제외해서 받아올 값이 4가지이니까
        try {
            String sql = "insert into breply(brindex , brcontent , no , bno) values(? , ? , ? , ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1 , Integer.parseInt(map.get("brindex")));    // map이 스트링 타입이므로 Integer.parseInt 강제 형변환 필요
            ps.setString(2 , map.get("brcontent"));
            ps.setInt(3 , Integer.parseInt(map.get("no")));
            ps.setInt(4 , Integer.parseInt(map.get("bno")));
            int count = ps.executeUpdate(); // 왜? if( count == 1 ) 하는지? executeUpdate의 반환 타입이 int 이기 때문에
            if(count==1){
                return true;    // 왜 true false 인지 댓글 쓰기의 성공여부 판단을 위해
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // bReplyWrite() end

    // 8. 댓글출력
    public  List<Map<String , String>> bReplyRead(int bno){
        System.out.println("BoardDao.bReplyRead");
        System.out.println("bno = " + bno);
        List<Map<String, String>> list = new ArrayList<>();
        try {
            String sql = "select * from breply inner join member on breply.no = member.no where bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Map<String , String> map = new HashMap<>();
                map.put("brno" , String.valueOf(rs.getInt("brno")));
                map.put("brcontent" , rs.getString("brcontent"));
                map.put("id" , rs.getString("id"));
                map.put("brdate" , rs.getString("brdate"));
                list.add(map);
                System.out.println(list);
            }
        }catch (Exception e){

        }
        return list;
    }   // bReplyRead() end

}   // class end
