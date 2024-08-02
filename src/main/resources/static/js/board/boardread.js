console.log('boardread.js');

// boardgetall.js 에서 boardread 페이지 이동 코드 <td><a href="/board/getread?bno=${result.bno}">${result.btitle}</a></td>
    // JS 코드가 HTML 만들어내고 사용자는 표현된 HTML 에서 a 클릭 이벤트
    // <a href="/board/getread?bno=3">안녕하세요</a>    ---> /board/getread?bno=3

// URL 상의 쿼리스트링 매개변수를 JS에서 꺼내는 방법
    // JAVA SPRING에서 HTTP URL 상의 쿼리스트링 꺼내는 방법
        // @RequestParam 이용한 쿼리스트링 매개변수 매핑
    // JS에서 HTTP URL 상의 쿼리스트링 매개변수를 꺼내는 방법
        // 1. new URL(location.href) : 현재 JS가 포함된 URL 경로
        // 2. .searchParams;         : 현재 경로상의 쿼리스트링 매개변수 속성 호출
        // 3. .get(key)              : 쿼리스트링 매개변수의 key의 해당하는 value 호출 

//1.
let urlParams = new URL(location.href).searchParams;
let bno = urlParams.get("bno") // 현재 URL 경로상의 bno 값 호출
console.log(bno);
// 한줄로 줄이기
// let bno =  new URL(location.href).searchParams.get("bno");

boardRead();
//2. 페이지 열릴 때 출력하기 , 매개변수는 현재 게시물의 번호
function boardRead(){ // 어디에 무엇을 {boardNo : bno, title : bTitle, userId : uId, bDate : writtenDate, bView : view, bContent : content}
    let board = {};
    $.ajax({
        async : false , 
        method:'get',
        url:"/board/read",
        data:{bno:bno},
        success:(r) =>{
           console.log(r);
           board = r;
        }
    })  // ajax end
    // 1. 어디에
    document.querySelector(".bcName").innerHTML = `${board.bcname}`;
    document.querySelector(".etcBox").innerHTML = `<span> 작성자 ${board.id} </span>
                                                    <span> 조회수 ${board.bview} </span>
                                                    <span> 작성일 ${board.bdate} </span>`;
    document.querySelector(".bTitle").innerHTML = `${board.btitle}`;
    document.querySelector(".bContent").innerHTML = `${board.bcontent}`;
    if( board.bfile == null){   // 첨부파일이 없을 때
        document.querySelector(".bfile").innerHTML = '';
    }else{  // 첨부파일이 있을 때
        document.querySelector(".bfile").innerHTML = `${board.bfile.split('_')[1]} <a href="/file/download?filename=${board.bfile}">다운로드</a>`; 
    }
    document.querySelector(".btnBox").innerHTML = `<button class="btn btn-primary" type="button" onclick="location.href='/board/edit?bno=${bno}'">수정</button>
                                                                <button class="btn btn-primary" type="button" onclick="bDelete(${bno})">삭제</button>`;
}   // boardRead() end

function bDelete(bno){
    console.log('bDelete()');
    $.ajax({
        async : false,
        method : 'delete',
        data : {bno : bno} , 
        url : "/board/delete",
        success:(r) =>{ 
            console.log(r);
            if(r){
                alert('삭제성공');
                location.href="/board/getall"
            }else{
                alert('본인이 작성한 글만 삭제 가능합니다.');
            }
        } ,
        error : (e) => {
            console.log(e)
        }
    })
}   // bDelete() end

// 3. 댓글쓰기
function onReplyWrite(){
    console.log('onReplyWrite()');
    // 1. 입력받은 값 가져오기
    let brcontent = document.querySelector(".brcontent").value;
    // 2. 객체화
    let info = { 
        brindex : 0 ,  // 0: 댓글분류 , 0이면 상위댓글
        brcontent : brcontent , 
        bno : bno   // 현재 보고 있는 게시물 번호(js 상단에 선언된 변수)
    };
    console.log(info);
    $.ajax({
        async : false , 
        method : 'post' , 
        url : "/board/reply/write" , 
        data : JSON.stringify(info) , // 객체를 JSON 문자열로 변환
        contentType : "application/json" , 
            // - contentType : "application/x-www-form-urlencoded" --> ajax 기본값(생략시)
            // - contentType : false --> multipart/form-data 첨부파일(바이너리)
            // - contentType : "application/json" 
        success : (r) => {
            console.log(r);
            if(r == true){
                alert("댓글쓰기 성공");
                document.querySelector(".brcontent").value = '';
                bReplyRead();
            }else{
                alert("댓글쓰기 실패 : 로그인 후 쓰기가 가능합니다.");
                location.href = "/member/login";
            }
        } , // success end
        error : (e) => {
            console.log(e);
        }   // error end
    })  // ajax end
}   // onReplyWrite() end

bReplyRead();
// 4. 댓글 출력
function bReplyRead(){
    console.log('bReplyRead()');
    let reply = {};
    $.ajax({
        async : false , 
        method : 'get' , 
        url : "/board/reply/read" , 
        data : {bno : bno} , 
        success : (r) =>{
            console.log(r);
            reply = r;
            console.log(reply);
        } , 
        error : (e) => {
            console.log(e); 
        }
    }); // ajax end
    // 어디에 
    let replyBox = document.querySelector(".replyBox");
    // 무엇을
    let html = ``;
    reply.forEach(rp => {
        html += `<tr>
                    <td> ${rp.id} </td>
                    <td> ${rp.brcontent} </td>
                    <td> ${rp.brdate} </td>
                </tr>
        `;
    });
    // 출력
    replyBox.innerHTML = html;
}   // bReplyRead() end
