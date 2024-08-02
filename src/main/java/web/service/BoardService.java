package web.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;
import web.model.dto.BoardPageDto;
import web.model.dto.MemberDto;

import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    BoardDao boardDao;
    @Autowired
    MemberService memberService;
    @Autowired
    FileService fileService;

    @Autowired // 현재 요청을 보낸 클라이언트의 HTTP 요청정보를 가지고 있는 객체를 주입
    HttpServletRequest request;

    // 1. 글 전체 출력
    public BoardPageDto bAllPrint( BoardPageDto pageDto) {
        System.out.println("pageDto = " + pageDto);
        // - 만약에 페이지번호가 매개변수로 존재하지 않으면 1페이지로 설정
        if(pageDto.getPage() == 0){
            pageDto.setPage(1);
        }
        // 1. 하나의 페이지당 표시할 게시물 수
        int pageBoardSize = 2;  // - 하나의 페이지당 5개씩 표기
        // 2. 페이지당 게시물을 출력할 시작 레코드 번호
        int startRow = (pageDto.getPage()-1)*pageBoardSize;

        // 4. 전체 게시물 수 : 카테고리번호 별 전체 게시물 , 검색 조건
        int totalBoardSize = boardDao.getTotalBoardSize(pageDto.getBcno() , pageDto.getSearchKey() , pageDto.getSearchKeyword());

        System.out.println("totalBoardSize = " + totalBoardSize);
        // 3. totalPage : 전체 페이지 수 구하기
            // 총 페이지수 계산식 : 전체 게시물 수 / 페이지당 , 몫 : 페이지수 , 나머지가 존재하면 페이지수 +1
                /* ex) 총게시물수 : 23개 , 페이지당 5개씩 게시물 출력 , 총 페이지 수 : 4페이지 +1 => 5페이지
                       총게시물수 : 20개 , 페이지당 5개씩 게시물 출력 , 총 페이지 수 : 4페이지
                       총게시물수 : 15 , 페이지당 10개씩 게시물 출력 , 총 페이지 수 : 1페이지 +1 => 2페이지 *
                */
        int totalPage = totalBoardSize % pageBoardSize == 0 ? // 전체 게시물수 나누기 페이지당 게시물수 했을 때 나머지가 없으면
                        totalBoardSize / pageBoardSize :      // 전체 게시물수 나누기 페이지당 게시물수의 몫을 전체 페이지수
                        totalBoardSize / pageBoardSize + 1;   // 나머지 게시물들을 출력할 페이지 1개 더해준다 , 전체 페이지수 + 1;

        // 5. 페이지별 시작 버튼 번호 , 끝 버튼 번호
            /*
                ex)
                    가정 : 총페이지 수 13이고 페이지당 최대 버튼 수 5개씩 , 몫 활용한 start
                   start 계산 식 : ((현재페이지 -1)/최대버튼수)*최대번튼수 + 1
                   end 계산 식 : start + 최대번트수 -1 , 단 end는 최대페이지수보다 커질 수 없다.
                                                                    page        start       end     , page -1 , / 최대버튼수(5) , 몫 , *최대버튼수 , +1
                    1페이지 때 버튼 출력 :  [1] [2] [3] [4] [5]         1            1          5           0           0/5        0        0       1
                    2페이지 때 버튼 출력 :  [1] [2] [3] [4] [5]         2            1          5           1           1/5        0        0       1
                    3페이지 때 버튼 출력 :  [1] [2] [3] [4] [5]         3            1          5           2           2/5        0        0       1
                    4페이지 때 버튼 출력 :  [1] [2] [3] [4] [5]         4            1          5           3           3/5        0        0       1
                    5페이지 때 버튼 출력 :  [1] [2] [3] [4] [5]         5            1          5           4           4/5        0        0       1
                    6페이지 때 버튼 출력 :  [6] [7] [8] [9] [10]        6            6          10          5           5/5        1        5       6
                    7페이지 때 버튼 출력 :  [6] [7] [8] [9] [10]        7            6          10          6           6/5        1        5       6
                    8페이지 때 버튼 출력 :  [6] [7] [8] [9] [10]        8            6          10          7           7/5        1        5       6
                    9페이지 때 버튼 출력 :  [6] [7] [8] [9] [10]        9            6          10          8           8/5        1        5       6
                    10페이지 때 버튼 출력 :  [6] [7] [8] [9] [10]       10           6          10          9           9/5        1        5       6
                    11페이지 때 버튼 출력 :  [11] [12] [13]             11           11         13          10          10/5       2        10      11
                    12페이지 때 버튼 출력 :  [11] [12] [13]             12           11         13          11          11/5       2        10      11
                    13페이지 때 버튼 출력 :  [11] [12] [13]             13           11         13          12          12/5       2        10      11
             */
        int btnSize = 5;    // 페이지당 최대 버튼 수를 5개씩 표기한다는 가정
        // 페이지별 시작 버튼 번호 변수
        int startBtn = ((pageDto.getPage()-1)/btnSize)*btnSize + 1;
        // 페이지별 끝 버튼 번호 변수
        int endBtn = startBtn + btnSize -1;
        // 만일 끝 번호가 마지막 페이지보다 커질 수 없다.
        if(endBtn >= totalPage)endBtn = totalPage;
        // 6. 게시물 정보 조회 : 페이지 처리 , 카테고리 별 , 검색조건
        List<BoardDto> data = boardDao.bAllPrint(startRow , pageBoardSize , pageDto.getBcno() , pageDto.getSearchKey() , pageDto.getSearchKeyword());

        // 반환 객체 구성
        BoardPageDto boardPageDto = BoardPageDto.builder()
                .page(pageDto.getPage())
                .totalBoardSize(totalBoardSize)
                .totalPage(totalPage)
                .data(data)
                .startBtn(startBtn)
                .endBtn(endBtn)
                .build();
        System.out.println("boardPageDto = " + boardPageDto);
        return boardPageDto;
    }   // bAllPrint() end

    // 2. 글 쓰기 카테고리 불러오기
    // public ArrayList<BoardDto> getBoardCategory() {
    public List<Map<String, String>> getBoardCategory() {
        System.out.println("BoardService.getBoardCategory");
        return boardDao.getBoardCategory();
    }

    // 3. 글 쓰기
    public boolean bWrite(BoardDto boardDto) {
        // 글 작성을 요청한 회원의 로그인 회원번호 구하기
        // 1. 로그인 세션에서 값 호출
        MemberDto loginDto = memberService.doLoginCheck();
        System.out.println("loginDto = " + loginDto);
        if (loginDto == null) return false;

        // 3. 속성 호출
        int loginMno = loginDto.getNo();
        // 4. boardDto에 담아주기
        boardDto.setNo(loginMno);

        // - 파일 업로드 처리
            // - 업로드된 파일 존재하면
            if(!boardDto.getUploadFile().isEmpty()){
                String uploadFileName = fileService.fileUpload(boardDto.getUploadFile());
                // 1. 만약에 업로드가 실패했으면 글쓰기 실패
                if (uploadFileName == null) return false;
                // 2. boardDto에 업로드된 파일명 넣어주기
                boardDto.setBfile(uploadFileName);
            }
        // - DB 처리
        return boardDao.bWrite(boardDto);
    }   // bWrite() end

    // 4. 상세페이지
    public BoardDto bRead(int bno){
        // 조회 수 증가 처리
        boardDao.viewIncrease(bno);
        return boardDao.bRead(bno);
    }

    // 5. 글 삭제
    public boolean bDelete(int bno){
        // 1. 로그인 세션에서 값 호출
        MemberDto loginDto = memberService.doLoginCheck();
        System.out.println("loginDto = " + loginDto);
        if (loginDto == null) return false;
        int loginMno = loginDto.getNo();
        return boardDao.bDelete(bno , loginMno);
    }   // bDelete() end

    // 6. 글 수정
    public boolean bUpdate(Map<String, String> map){
        System.out.println("BoardService.bUpdate");
        System.out.println("map = " + map);
        // 1. 로그인 세션에서 값 호출
        Object object = memberService.doLoginCheck();
        System.out.println("object = " + object);
        if (object == null) return false;
        MemberDto loginDto = (MemberDto) object;
        int loginMno = loginDto.getNo();
        map.put("no", String.valueOf(loginMno));
        return boardDao.bUpdate(map);
    }   // bUpdate() end

    // 7. 게시물의 댓글 쓰기 (기능) 처리 // 왜 postmapping이 없는지 컨트롤러가 통신 역할을 하고 서비스의 경우 비즈니스 로직을 실행하는 곳이기 때문 패턴 규칙
    public boolean bReplyWrite(Map<String , String> map){
        System.out.println("BoardService.bReplyWrite");
        System.out.println("map = " + map);
        // 작성자(no) 는 별도의 클라이언트로부터 입력받는 구조가 아니다.
            // - 회원제 댓글이라는 가정(로그인 정보는 로그인 객체 저장된 상태)
            // 왜? 로그인 정보는 세션 객체에 저장하는지 다른 곳에서 또 써야하기 때문에 사용자의 상태를 추적하기 위해
        Object object = memberService.doLoginCheck();   // // 왜 ?? object 사용하는지?? 세션에 속성값을 저장하는 타입이 Object 타입이라서
        if(object == null){
            return false;
        }
        MemberDto loginDto = (MemberDto) object;
        int no = loginDto.getNo();
        map.put("no" , String.valueOf(no));     // int 타입을 String 타입으로 바꿔주기
        return boardDao.bReplyWrite(map); // 왜 dao인지 -> controller service dao 관계 파악하기
    }   // bReplyWrite() end

    // 8. 댓글출력
    public  List<Map<String , String>> bReplyRead(int bno){
        System.out.println("BoardService.bReplyRead");
        System.out.println("bno = " + bno);
        return boardDao.bReplyRead(bno);
    }   // bReplyRead() end

}   // class end
