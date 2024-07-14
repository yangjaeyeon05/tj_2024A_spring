console.log("todo.js");

get();
function get(){
    console.log("get()");
    let todoBox = document.querySelector(`#todoBox`);           // 출력할 HTML의 div 부분의 id를 todoBox에 대입
    let html = ``;
    // 1. ajax 옵션객체 정의
    let option = {
        // url : "http://localhost:8080/example/rest3?key=qwe" ,   // 통신할 URL , 스프링 컨트롤러 매핑 주소
        url : "/todo/print" ,    // IP와 PORT 생략
        method : 'get' ,                 // 통신할 HTTP 메소드 선택
        success : function(response){    // 통신 성공 시 응답받은 데이터

            response.forEach(todo => {
                if(todo.state == 0){                          // e == 'X'는 기본값으로 등록했을 때 화이트 박스가 나오게 하기 위함.
                    html +=`<div id="whiteBox">
                                <span> ${todo.content} </span>
                                <div>
                                    <button type="button" onclick="change(${todo.tno})">변경</button>
                                    <button type="button" onclick="remove(${todo.tno})">삭제</button>
                                </div>
                            </div>`            
                }else if(todo.state == 1){
                    html +=`<div id="blackBox">
                                <span> ${todo.content} </span>
                                <div>
                                    <button type="button" onclick="change(${todo.tno})">변경</button>
                                    <button type="button" onclick="remove(${todo.tno})">삭제</button>
                                </div>
                            </div>`
                }
            });  
            todoBox.innerHTML = html;    
        }   // success end
    // 2. ajax 메소드에 옵션 넣어서 실행
    }
    $.ajax(option);
}

function post(){
    console.log("post()");
    let todoInput = document.querySelector("#todoInput").value;
    $.ajax({
        url : "/todo/save" , // HTTP 통신할 경로URL , 컨트롤러 매핑
        method : 'post' ,                // HTTP 메소드
        data : { "key" : todoInput } , 
        success : function(response){    // HTTP 성공응답 , 컨트롤러가 return한 값
            console.log(response);
            alert('리스트 저장 성공');
            get();
        }
    }); // ajax end
}

function change(tno){
    console.log("change()");
    $.ajax({
        url : "/todo/update" , // HTTP 통신할 경로URL , 컨트롤러 매핑
        method : 'put' ,                // HTTP 메소드
        data : { "key" : tno } , 
        success : function(response){    // HTTP 성공응답 , 컨트롤러가 return한 값
            console.log(response);
            alert('리스트 수정 성공');
            get();
        }
    }); // ajax end
}
function remove(tno){
    console.log("remove()");
    $.ajax({
        url : "/todo/delete" , // HTTP 통신할 경로URL , 컨트롤러 매핑
        method : 'delete' ,                // HTTP 메소드
        data : { "key" : tno } , 
        success : function(response){    // HTTP 성공응답 , 컨트롤러가 return한 값
            console.log(response);
            alert('리스트 삭제 성공');
            get();
        }
    }); // ajax end
}