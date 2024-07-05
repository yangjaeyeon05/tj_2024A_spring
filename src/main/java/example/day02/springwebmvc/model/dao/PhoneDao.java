package example.day02.springwebmvc.model.dao;

import example.day02.springwebmvc.model.dto.PhoneDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PhoneDao {
    // 1. 싱글톤 패턴
    private static PhoneDao phoneDao = new PhoneDao();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    private PhoneDao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springexample" , "root" , "1234");
        }catch (Exception e){
            System.out.println(">> 연동 실패"+e);
        }   // try end
    }
    public static PhoneDao getInstance(){
        return phoneDao;
    }

    // 1.
    public boolean postPhone(PhoneDto phoneDto){
        try{
            String sql = "insert into phonebook(name , phone) values(? , ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,phoneDto.getName());
            ps.setString(2,phoneDto.getPhone());
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // postPhone() end

    // 2.
    public ArrayList<PhoneDto> getPhone(){
        ArrayList<PhoneDto> list = new ArrayList<>();
        try {
            String sql = "select * from phonebook ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                PhoneDto phoneDto = new PhoneDto(id , name , phone);
                list.add(phoneDto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }   // getPhone() end{

}   // class end
