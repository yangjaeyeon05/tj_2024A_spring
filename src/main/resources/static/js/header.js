console.log('header.js');

// 1. 로그인 상태 요청
doLoginCheck();
function doLoginCheck(){
    $.ajax({
        async : false , // 동기화
        method : 'get' ,
        url : "/member/login/check" ,
        success : (result)=>{console.log(result);
            let html = '';
            // 로그인 상태에 따른 메뉴 구성
            if(result !== ''){
            console.log('로그인');
            // 로그인 일때
                html += `<li class="nav-item"> ${result.id}님 </li>
                        <li class="nav-item"> <a class="nav-link" href="#" onclick="doLogout()"> 로그아웃 </a> </li>
                        <li class="nav-item"> <a class="nav-link" href="/member/mypage"> 마이페이지 </a> </li> `;
            }else{
            console.log('비로그인');
            // 비로그인일때
                html += `<li class="nav-item"> <a class="nav-link" href="/member/signup"> 회원가입 </a> </li>
                        <li class="nav-item"> <a class="nav-link" href="/member/login"> 로그인 </a> </li>`;
            }
            document.querySelector("#loginMenu").innerHTML = html;
        } , 
        error : (e)=>{
            console.log(e);
        }
    })  // ajax end
}   // doLoginCheck() end

// 2. 로그아웃
function doLogout(){
    $.ajax({
            method : 'get' ,
            url : "/member/logout" ,
            success : (result)=>{console.log(result);
                location.href="/";
            }
    })  // ajax end
}   // doLogout() end

