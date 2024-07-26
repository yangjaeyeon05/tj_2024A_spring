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
function boardRead(){ // 어디에 무엇을 {boardNo : brdNo, title : bTitle, userId : uId, bDate : writtenDate, bView : view, bContent : content}
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
    let bcName = document.querySelector(".bcName").innerHTML = `${board.bcname}`;
    let etcBox = document.querySelector(".etcBox").innerHTML = `${board.id}/${board.bview}/${board.bdate}`;
    let bTitle = document.querySelector(".bTitle").innerHTML = `${board.btitle}`;
    let bContent = document.querySelector(".bContent").innerHTML = `${board.bcontent}`;
    let bfile = document.querySelector(".bfile").innerHTML = `${board.bfile} <a href="/file/download?filename=${board.bfile}">다운로드</a>`; 
    let btnBox = document.querySelector(".btnBox").innerHTML = `<button class="btn btn-primary" type="button" onclick="location.href='/board/edit?bno=${bno}'">수정</button>
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
