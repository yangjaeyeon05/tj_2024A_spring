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
            <div class="row">제목 : ${result.btitle}</div>
            <div class="row">내용 : ${result.bcontent}</div>
            <div class="row">아이디 : ${result.id}</div>
            <div class="row">작성일 : ${result.bdate}</div>
            <div class="row">조회수 : ${result.bview}</div>
            `
            boardBox.innerHTML=divHTML;
        }
        
    })
    
    
}
