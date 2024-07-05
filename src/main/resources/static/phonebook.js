console.log("phonebook.js 열림")
getPhone();
// let phoneBookDB = [];
// 1. 등록함수 : 등록버튼을 클릭했을때
function postPhone(){
    // 1. 입력받고
    let name = document.querySelector('#name').value;
    let phone = document.querySelector('#phone').value;
    // 2. 객체화
    let phoneDto = {
        name : name , phone : phone
    }
    // 3. 컨트롤에게 통신
    // phoneBookDB.push(phoneDto);
    // 4. 안내 / 새로고침
    // alert('save');
    // getPhone();
    // 2. html에 jquery 라이브러리 가져왔으면 ajax 함수 사용 가능.
        // 2-1 ajax 들어갈 옵션 객체 정의
    let option = {
        url : "http://localhost:8080/phone/create" ,   // 통신할 경로 --> spring의 controller
        method : "post" ,   // HTTP가 지원하는 함수 중 사용할 함수명( post , get , put , delete 등 )
        data : JSON.stringify(phoneDto) ,   // 통신할 경로에 보낼 데이터 --> spring의 controller에게 보낼 데이터
        contentType : "application/json",     // data 옵션에 있는 타입
        success : function response( result ){  // 통신을 성공했을 때 응답받을 함수 정의하고 매개변수는 응답의 데이터가 들어온다.
            console.log(result);
            if(result){
                alert('save'); getPhone();
            }else{
                alert('fail');
            }
        }  // success response end
    }   // option{} end
        // 2-2 ajax 함수 호출   , $ : jquery 문법
    $.ajax(option);
}
// 2. 출력함수 : 등록처리가 되었을 때 , js 열렸을 때 최초1번
function getPhone(){
    // 1. 어디에
    let phoneListBox = document.querySelector('#phoneListBox');
    // 2. 무엇을
    let html =``;
    let option = {
        url : "http://localhost:8080/phone/read" ,      // 누구에게
        method : "get" ,                                // 어떤 방식으로
        // data :  ,                                    // 무엇을 보내고 (매개변수없음)
        success : function response(result){            // 무엇을 받을지
            console.log(result);
            result.forEach( phone => {
                    html += `
                        <div><span> ${phone.name} </span> <span> ${phone.phone} </span></div>
                    `
                })
        // 3. 출력
        phoneListBox.innerHTML = html;
        }
    }   // ajax 통신 option 설정 end
    $.ajax(option);
}



