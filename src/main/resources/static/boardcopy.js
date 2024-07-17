/*

  요구사항 확인
    [ 비회원 게시판 ]
    1. 글 등록 하는데 비회원제 이면서 제목 과 내용 과 비밀번호 입력 해주세요.
    2. 모든 글 출력하는데 날짜 , 제목 , 조회수 순으로 출력해주세요. 
    3. 모든 글에서 제목을 클릭하면 상세 글 이 출력되고 모든 정보 출력 될수 있도록 해주세요.
      <날짜 , 제목 , 조회수 , 내용 >
    4. 상세 글에서 수정시 비밀번호를 입력받아 일치할 경우에만 새로운 내용을 입력받고 수정해주세요.
    5. 상세 글에서 삭제시 비밀번호를 입력받아 일치할 경우에만 삭제
  
  -요구사항 분석 -> 설계
    [ 백엔드 ]
    1.메모리 구성
      1. 등록용:제목 , 내용 , 비밀번호 
      2. 출력용:날짜 , 조회수 
      - 1안(배열) :  배열 5개 
        1. title = [ title1, title2 , title3 ] , 
          content = [ content1 ,content2,content3 ] , 
          password = [ password1 , password2 , password3] , 
          date = [ date1 , date2 , date3 ] , 
          view = [ view1 , view2 , view3 ]

        2. boardList = [ 
              [ title1,content1,password1,date1 ] ,
              [ title2,content2,password2,date2 ] ,
              [ title3,content3,password3,date3 ] 
            ]

      - 2안(문자열)
          boardList = [ "title1,content1,password1,date1" , "title2,content2,password2,date2" , "title3,content3,password3,date3" ]
            - " " 하나의 문자열이 하나의 게시물이 되고 , 쉼표 기준으로 게시물의 정보 구분한다.
          boardList = "title1,content1,password1,date1\ntitle2,content2,password2,date2\ntitle3,content3,password3,date3"
            - 전체 게시물을 " " 하고 각 게시물은 \n 구분하고 , 쉼표는 각 게시물의 정보를 분류한다.

      - 3안(객체)
    2.기능 구성 
      1. 등록 create( )
      2. 모든글출력 allRead( )
      3. 상세글출력 read( )
      4. 수정 update( )
      5. 삭제 delete( )
    3.기능 마다의 로직 순서도  
      1. create( ) : 1. HTML에서 입력받은 값 가져오기 2.유효성검사 3.배열 저장 4. 안내                                                                
      2. allRead( ) : 1. (어디에)출력할html요소호출 2. (무엇을)반복문 이용한 배열내 데이터를 HTML형식 구성 3.(출력/대입) 구성한html를 요소에 대입           
      3. read( )    : 1. 상세정보의 인덱스를 받고 2. 해당 인덱스의 정보를 출력한다( 어디에 무엇을 출력 )                                                
      4. update( ) : 1. 업데이트할 인덱스를 받고 2. 비밀번호를 새롭게 입력받아 3. 입력받은 비밀번호와 해당 인덱스의 비밀번호와 일치하면 4. 해당 인덱스 수정 
      5. delete() : 1. 삭제할 인덱스를 받고 2. 비밀번호를 새롭게 입력받아 3. 입력받은 비밀번호와 해당 인덱스의 비밀번호와 일치하면 4. 해당 인덱스 삭제       
    [ 프론트엔드 ]
    1. HTML 구성 

  개발/구현 
    [ 각 파트별 구현 ]
    [ 연동 ]
  테스트

  유지보수
*/

// 1. 등록함수 실행조건 : 1.등록버튼을 클릭했을때 
function boardCreate(){ 
  console.log('boardCreate()');
  // 1-1 
  let title = document.querySelector('#titleInput').value;
  let content = document.querySelector('#contentInput').value;
  let password = document.querySelector('#passwordInput').value;

  // [2] AJAX(JQUERY라이브러리) 이용한 웹서버(컨트롤러) 통신해서 요청과 응답하기
  $.ajax({
    method : 'post' ,                // HTTP 메소드
    url : "/boardcopy/create" , // HTTP 통신할 경로URL , 컨트롤러 매핑
    data : {"btitle" : title , "bcontent" : content , "bpwd" : password} , 
    success : function(response){    // HTTP 성공응답 , 컨트롤러가 return한 값
        console.log(response);      // 응답 결과 확인
        if(response==true){
            alert('게시판 등록 성공');   // 안내 후
            titleInput.value = '';       // 입력상자에 입력된 값 없애기
            contentInput.value = '';
            passwordInput.value = '';
            boardPrintAll();             // 할일 목록 전체 출력 함수 호출
        }else{
            alert('게시판 등록 실패');
        }
    }
  }); // ajax end
}

boardPrintAll();
// 전체 글 출력
function boardPrintAll(){
  console.log("boardPrintAll()");
  // - 출력할 데이터 가져오기
  $.ajax({
    method : 'get' , 
    url : '/boardcopy/printAll' , 
    success : function(response){
        console.log(response);  // 경과받은 데이터의 타입은 array/list
        // 어디에
        let tableBody = document.querySelector(`#tableBody`);
        // 무엇을
        let html = ``;
        // [1] for(let i = 0; i < response.length;i++){}
        // [2] 리스트명.forEach(반복변수명 => {실행할 코드 })
        response.forEach(boardDto => {
                html += `<tr> 
                <td> ${ boardDto.bno } </td> 
                <td onclick="boardPrint( ${ boardDto.bno } )"> ${ boardDto.btitle } </td> 
                <td> ${ boardDto.bdate } </td>
                <td> ${ boardDto.bview } </td>
                </tr>`
        });
        // 출력
        tableBody.innerHTML = html; 
    }
  }); // ajax end
}

// 상세글 출력
function boardPrint(bno){
  $.ajax({
    method : 'get' , 
    url : '/boardcopy/print' , 
    data : {"bno" : bno} , 
    success : function(response){
        console.log(response);  // 경과받은 데이터의 타입은 array/list
        // 어디에
         // 1. 어디에 
        let viewPage = document.querySelector('#viewPage');
        // 2. 무엇을 
        let html = `<h3> 상세 페이지 </h3>
                    <div> ${ bno } </div>
                    <div> 
                      <span> 조회수 : ${ response.bview } </span> 
                      <span> 작성일 : ${ response.bdate } </span>
                    </div>
                    <div> ${ response.bcontent } </div>
                    <button onclick="confirmPwd( ${ bno } )">수정</button>
                    <button onclick="confirmPwd2( ${ bno } )">삭제</button>`;
        // 3. 출력/대입 
        viewPage.innerHTML = html;
        boardPrintAll();
    }
  }); // ajax end
}

// 게시판 수정
function boardUpdate(bno){
  console.log("boardUpdate()");
  let bcontent = prompt('수정할 내용 :');
  $.ajax({
      method : 'put' ,                // HTTP 메소드
      url : `/boardcopy/update` , // HTTP 통신할 경로URL , 컨트롤러 매핑
      data : { "bno" : bno , "bcontent" : bcontent} , 
      success : function(response){    // HTTP 성공응답 , 컨트롤러가 return한 값
          console.log(response);
          if(response){
            boardPrint(bno);
          }else{
              alert('오류발생:관리자에게문의');
          }
      }
  }); // ajax end
}   // boardUpdate() end

// 게시판 삭제 
function boardDelete(bno){
  console.log("boardDelete()");
  $.ajax({
      method : 'delete' ,                // HTTP 메소드
      url : `/boardcopy/delete` , // HTTP 통신할 경로URL , 컨트롤러 매핑
      data : { "bno" : bno } , 
      success : function(response){    // HTTP 성공응답 , 컨트롤러가 return한 값
          console.log(response);
          if(response){
            boardPrintAll();  // 새로고침
            viewPage.innerHTML = '';
          }else{
              alert('오류발생:관리자에게문의');
          }
      }
  }); // ajax end
}   // boardDelete() end

// 비밀번호 확인
function confirmPwd(bno){
  console.log("confirmPwd()");
  let bpwd = prompt('비밀번호 : ');
  $.ajax({
      method : 'put' ,                // HTTP 메소드
      url : `/boardcopy/confirm` , // HTTP 통신할 경로URL , 컨트롤러 매핑
      data : { "bno" : bno , "bpwd" : bpwd } , 
      success : function(response){    // HTTP 성공응답 , 컨트롤러가 return한 값
          console.log(response);
          if(response){
            boardUpdate(bno);
          }else{
              alert('비밀번호 틀림');
          }
      }
  }); // ajax end
}   // boardUpdate() end

function confirmPwd2(bno){
  console.log("confirmPwd()");
  let bpwd = prompt('비밀번호 : ');
  $.ajax({
      method : 'put' ,                // HTTP 메소드
      url : `/boardcopy/confirm` , // HTTP 통신할 경로URL , 컨트롤러 매핑
      data : { "bno" : bno , "bpwd" : bpwd } , 
      success : function(response){    // HTTP 성공응답 , 컨트롤러가 return한 값
          console.log(response);
          if(response){
            boardDelete(bno);
          }else{
              alert('비밀번호 틀림');
          }
      }
  }); // ajax end
}   // boardUpdate() end


