console.log("signup.js");

// 1. 회원가입
function doSignup(){
    console.log("doSignup()");
    // 1. 입력값 가져오기
    let id = document.querySelector("#id").value;
    let pw = document.querySelector("#pw").value;
    let name = document.querySelector("#name").value;
    let email = document.querySelector("#email").value;
    let phone = document.querySelector("#phone").value;
    // 2. 객체
    let info = {
        id : id , pw : pw , name : name , email : email , phone : phone
    };
    console.log(info);
    // 3. ajax(jquery라이브러리 필요)
    $.ajax({
        async : false ,          // true : 비동기식 false : 동기식
        method : 'post' ,                // HTTP 메소드
        url : "/member/signup" , // HTTP 통신할 경로URL , 컨트롤러 매핑
        data : info ,               // HTTP 보낼 데이터
        success : (result)=>{    // HTTP 성공응답 , 컨트롤러가 return한 값
            console.log(result);      // 응답 결과 확인
            if(result==true){
                alert('회원가입 성공');   // 안내 후
                location.href="/member/login";
            }else{
                alert('회원가입 실패');
            }
        }
      }); // ajax end
      alert('ajax 처리 이후');
    // async : true  ,  alert('ajax 처리 이후'); -> alert('회원가입성공');
    // async : false ,  alert('회원가입성공'); ->  alert('ajax 처리 이후');
} // method end