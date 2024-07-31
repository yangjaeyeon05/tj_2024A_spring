console.log('getall.js');

// * 페이지 정보들을 관리하는 객체 , 전역변수 , 함수의 매개변수로 관리가 힘듬.
let pageInfo = {
    page : 1 , 
    bcno : 0 , 
    searchKey : 'btitle' , 
    searchKeyword : '' , 
}
// 1. page : 현재페이지[기본값1페이지] , 2. bcno : 현재 카테고리[기본값0전체보기
// 3. searchKey : 현재검색필드[기본값:제목] 4. searchKeyword : 현재검색값[기본값 : 공백]

// 5. 검색 상태 제거/초기화 함수
function onSearchClear(){
    // 1. 입력창 초기화
    document.querySelector('.searchKey').value = `btitle`;
    document.querySelector('.searchKeyword').value = ``;
    // 2. 전역변수 초기화
    pageInfo.searchKey = 'btitle';
    pageInfo.searchKeyword = '';
}

// 4. 카테고리 클릭했을때
function onCategory(bcno){
    // * 검색제거
    onSearchClear();
    // 1. 전역변수에 bcno 대입
    pageInfo.bcno = bcno;
    console.log('카테고리변경'); console.log(bcno);
    getall(1);  // 2. 새로고침 , 1 대입
}

// 3. 카테고리 호출
function getCategory(){
    console.log('getCategory()');
    // 1. 어디에
    let categoryBox = document.querySelector(".categoryBox");
    // 2. 무엇을
    let html = `<div class="${pageInfo.bcno == 0 ? 'categoryActive' : ''}" style="width: 70px;" onclick="onCategory(0)"> 전체보기 </div>`;
    console.log(html);
        $.ajax({
            async : false , 
            method : 'get' , 
            url : "/board/getcategory" , 
            success : (r) => {
                console.log(r);
                r.forEach(c => {
                    html += `<div class="${pageInfo.bcno == c.bcno ? 'categoryActive' : ''}" style="width: 70px;" onclick="onCategory(${c.bcno})"> ${c.bcname} </div>`;
                })
            } , 
            error : (e) => {
                console.log(e);
            }
        })
    // 3. 출력
    categoryBox.innerHTML = html;
}   // getCategory() end

// 검색 버튼을 클릭했을 때
function onSearch(){
    console.log('onSearch()');
    // 1. 입력받고
    let searchKey = document.querySelector('.searchKey').value;
    let searchKeyword = document.querySelector('.searchKeyword').value;
    // 2. 전역변수에 대입
    pageInfo.searchKey = searchKey;
    pageInfo.searchKeyword = searchKeyword;
    // 3. 새로고침 , 1페이지
    getall(1);


}   // onSearch() end

// 1. 전체 게시물 조회
    // 매개변수
    // 1. page : 보고자 하는 현재 페이지번호 , 초기값 : 1 , 첫페이지를 1페이지로 하기 위해서
getall(1);
function getall(page){
    console.log('getall()')
    pageInfo.page = page;   // 현재페이지 번호를 전역변수에 대입
    getCategory();  // 카테고리 호출
    let boardPageDto = {};  // ajax 응답 데이터 타입 Object
    
    $.ajax({
        async : false , // 동기화 vs true 비동기화 (innerHTML 후에 작동)
        method:'get',
        url:"/board/all",
        data : pageInfo , // 전역변수 보내기
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


