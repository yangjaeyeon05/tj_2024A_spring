package web.model.dao;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import web.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class MemberDao extends Dao{

    // 1. 회원가입
    public boolean doSignup(MemberDto memberDto){
        System.out.println("MemberDao.doSignup");
        System.out.println("memberDto = " + memberDto);
        try{
            String sql = "insert into member(id,pw,name,email,phone) values(? , ? , ? , ? , ?)";
            PreparedStatement ps = super.conn.prepareStatement(sql);
            ps.setString(1, memberDto.getId());
            ps.setString(2, memberDto.getPw());
            ps.setString(3, memberDto.getName());
            ps.setString(4, memberDto.getEmail());
            ps.setString(5, memberDto.getPhone());
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // doSignup() end

    // 2. 로그인 : 로그인 성공한 회원번호 반환(세션에 저장하려고)
    public int doLogin( MemberDto memberDto ){
        try{
            String sql = "select * from member where id = ? and pw = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getId());
            ps.setString(2, memberDto.getPw());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("no");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;   // 0은 회원번호가 없다는 뜻
    }   // doLogin() end

    // 3. 내정보 페이지
    public MemberDto getMypage(int no){
        try{
            String sql = "select * from member where no = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, no);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                MemberDto memberDto = new MemberDto();
                memberDto.setNo(rs.getInt("no"));
                memberDto.setId(rs.getString("id"));
                memberDto.setName(rs.getString("name"));
                memberDto.setEmail(rs.getString("email"));
                memberDto.setPhone(rs.getString("phone"));
                return memberDto;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }   // getMypage() end

}   // class end
