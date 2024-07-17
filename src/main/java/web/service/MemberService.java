package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;

@Service
public class MemberService {

    @Autowired
    MemberDao memberDao;

    public boolean doSignup(MemberDto memberDto){
        System.out.println("MemberService.doSignup");
        System.out.println("memberDto = " + memberDto);
        return memberDao.doSignup(memberDto);
    }   // doSignup() end

}   // class end

