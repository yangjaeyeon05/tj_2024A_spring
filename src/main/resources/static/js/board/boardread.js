//1.
let urlParams = new URL(location.href).searchParams;
let currentBno = urlParams.get("bno") //글번호

boardRead();
//2. 페이지 열릴 때 출력하기
function boardRead(){ // 어디에 무엇을 {boardNo : brdNo, title : bTitle, userId : uId, bDate : writtenDate, bView : view, bContent : content}
    let boardBox = document.querySelector("#boardBox")
    let divHTML = ''
    $.ajax({
        method:'get',
        url:"/board/read",
        data:{bno:currentBno},
        success:result =>{
            divHTML = `<div class="row">카테고리 : ${result.bcname}</div>
            <div class="row">글 번호 : ${result.bno}</div>
            <div class="row">아이디 : ${result.id}</div>
            <div class="row">작성일 : ${result.bdate}</div>
            <div class="row">조회수 : ${result.bview}</div>
            <div class="row">제목 : ${result.btitle}</div>
            <div class="row">내용 : ${result.bcontent}</div>
            <button class="btn btn-primary" type="button" onclick="bDelete(${result.bno})">삭제</button>
            <button class="btn btn-primary" type="button" onclick="location.href='/board/edit?bno=${result.bno}'">수정</button>
            `
            boardBox.innerHTML=divHTML;
        }
    })  // ajax end
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
}
