console.log('boardedit.js');

let urlParams = new URL( location.href ).searchParams;
let bno = urlParams.get('bno'); // 클릭된 게시물 번호
console.log(bno);


getBoardCategory();

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

function bUpdate(){
    //{"bcno" : bcno, "btitle" : btitle, "bcontent" : bcontent}
    // 1. 입력 값 가져오기
        // - select 목록에서 선택한 option의 value 값 가져오기
        let btitle = document.querySelector("#btitle").value;
        let bcontent = document.querySelector("#bcontent").value;
        let bcno = document.querySelector("#category").value;
        // 2. 객체화
        let info = {btitle : btitle, bcontent : bcontent, bcno : bcno , bno : bno}
        console.log(info);
        
        // 3. ajax 통신
        $.ajax({
            async : false,
            method : "put",
            url : "/board/edit",
            data : JSON.stringify(info),
            contentType : "application/json",
            // 4. 결과 처리
            success : (r) => {
                console.log(r);
                if (r) {
                    alert("수정 완료.")
                    location.href="/board/getall"
                } else {
                    alert("본인이 작성한 글만 수정 가능합니다.")
                }
            } , 
            error : (e)=>{
                console.log(e);
            }
        });  // ajax end
}