console.log('getall.js');
// 1. 전체 게시물 조회
    // 매개변수
    // 1. page : 보고자 하는 현재 페이지번호 , 초기값 : 1 , 첫페이지를 1페이지로 하기 위해서
    // 2. bcno : 보고자 하는 카테고리 번호 , 초기값 : 0 , 전체보기를 0으로 하기 위해서
getall(1 , 0);
function getall(page , bcno){
    let boardPageDto = {};  // ajax 응답 데이터 타입 Object
    
    $.ajax({
        async : false , // 동기화 vs true 비동기화 (innerHTML 후에 작동)
        method:'get',
        url:"/board/all",
        data : {page : page , bcno : bcno} , 
        success : (r) =>{
            console.log(r);
            // 응답 데이터의 타입이 Array , Object 인지 확인 필요
            boardPageDto = r;
        } , 
        error : (e)=>{
            console.log(e);
        }
    })  // ajax end
    let board=document.querySelector('#list');
    let html='';
    let list = boardPageDto.data;
    list.forEach(b =>{
        console.log(b);
        html+=`<tr>
                <th>${b.bno}</th>
                <td><a href="/board/getread?bno=${b.bno}">${b.btitle}</a></td>
                <td>${b.id}</td>
                <td>${b.bdate}</td>
                <td>${b.bview}</td>
                </tr>`;
    });
    board.innerHTML=html;

    // 4. 페이지네이션(페이지버튼) 구성
        // 4-1 어디에
        let pagination = document.querySelector(".pagination");
        // 4-2 무엇을
        let pageHTML = '';
            // 이전버튼 , page : 현재 함수의 매개변수이면서 현재 페이지 번호 의미 , 현재페이지 -1 , 만약에 -1 했을 때 1보다 작으면 1 고정하고 아니면 -1 차감
            pageHTML += `<li class="page-item"><a class="page-link" onclick="getall(${page-1 < 1 ? 1 : page-1})">이전</a></li>`;
            // 페이지버튼
                // 페이지 마다 시작 버튼 번호
                let startBtn = boardPageDto.startBtn;
                // 페이지 마다 끝 버튼 번호 
                let endBtn = boardPageDto.endBtn;
                // 최대 페이지수 : totalPage
                let totalPage = boardPageDto.totalPage;
                // * current 는 startBtn 부터 endBtn 까지 반복
                // current == page ? 'active' : '' : current번째 값이 현재 페이지이면 active 클래스
            for(let current = startBtn; current <= endBtn; current++){
                pageHTML += `<li class="page-item"><a class="page-link ${current == page ? 'active' : ''}" onclick="getall(${current})">${current}</a></li>`;
            }
            // 다음 버튼  , page : 현재 함수의 매개변수이면서 현재 페이지 번호 의미 , 현재페이지 +1 , 만약에 현재 페이지 +1 했을때 최대 페이지 수보다 커지면 최대 페이지수 고정
            pageHTML += `<li class="page-item"><a class="page-link" onclick="getall(${page+1 > totalPage ? totalPage : page+1})">다음</a></li>`;
        // 4-3 출력
        pagination.innerHTML = pageHTML;

}   // getall() end

// 보통 수업에서 출력을 success 안에 넣음 
// 밖으로 빼고 싶으면 list 변수에 r 을 넣어주고
// ajax를 동기화로 바꿔줘야한다.


