console.log('boardwrite.js');

getBoardCategory();
// loginCheck();

// 1. 카테고리 호출 , 실행조건 : js열렸을 때
function getBoardCategory(){
    $.ajax({
        async:false,
        method:'get',
        url:"/board/getcategory",
        success:(r) =>{
            console.log(r);
            // 어디에
            let category = document.querySelector("#category");
            // 무엇을
            let html = ``;
                // - 서버로부터 응답받은 데이터를 타입 확인했더니 , List 타입이므로 반복문 사용하자.
                    // 언어별 화살표 함수 표현 방식 JAVA : () -> {}, JS : () => {}
            r.forEach(c =>{
                html+=`<option value="${c.bcno}">${c.bcname}</option>`;
            })
            // 출력
            category.innerHTML=html;
        } ,
        error : (e) => {
            console.log(e)
        }
    })
}   // getCategory() end

/*
// 2. 게시물 쓰기( 첨부파일을 전송하지 않는 일반 JSON 타입의 통신)
function bWrite(){
    
    //{"bcno" : bcno, "btitle" : btitle, "bcontent" : bcontent}
    // 1. 입력 값 가져오기
        // - select 목록에서 선택한 option의 value 값 가져오기
    let btitle = document.querySelector("#btitle").value;
    let bcontent = document.querySelector("#bcontent").value;
    let bcno = document.querySelector("#category").value;
    // 2. 객체화
    let info = {btitle : btitle, bcontent : bcontent, bcno : bcno}
    console.log(info);
    
    // 3. ajax 통신
    $.ajax({
        async : false,
        method : "POST",
        url : "/board/write",
        data : JSON.stringify(info),
        contentType : "application/json",
        // 4. 결과 처리
        success : (r) => {
            console.log(r);
            if (r) {
                alert("글쓰기 완료.")
                // location.href="/board/getall"
            } else {
                alert("글쓰기 오류.")
            }
        } , 
        error : (e)=>{
            console.log(e);
        }
    });  // ajax end
}   // bWrite() end
*/

// 2. 게시물 쓰기( 첨부파일을 전송하는 대용량 form 타입의 통신)
function bWrite(){
    // 1. form 가져오기 , form 안에 있는 HTML 모두 한번에 가져오기
    let boardWriteForm = document.querySelector(".boardWriteForm");
    console.log(boardWriteForm);
    // 2. form HTML을 바이트로 변환해주는 객체 = new FormData
    let boardWriteFormData = new FormData(boardWriteForm);
    console.log(boardWriteFormData);
    // 3. AJAX 통신
    $.ajax({
        method : 'post' , 
        url : "/board/write" , 
        data : boardWriteFormData , 
        contentType : false , processData : false , 
        success : (r) => {
            console.log(r);
            if (r) {
                alert("글쓰기 완료.")
                location.href="/board/getall"
            } else {
                alert("글쓰기 오류.")
            }
        } , 
        error : (e) =>{
            console.log(e);
        }
    });
}   // bWrite() end


function loginCheck(){
    $.ajax({
        async : false,
        method : "GET",
        url : "/member/my/info",
        success : response => {
            if (!response.id){
                alert("먼저 로그인해 주세요.")
                location.href="/member/login"
            }
        }
    })
}



