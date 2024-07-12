console.log("todo.js");

let todoList = [];

get();
function get(){
    console.log("get()");
    // 1. ajax 옵션객체 정의
    let option = {
        // url : "http://localhost:8080/example/rest3?key=qwe" ,   // 통신할 URL , 스프링 컨트롤러 매핑 주소
        url : "/todo/print" ,    // IP와 PORT 생략
        method : 'get' ,                 // 통신할 HTTP 메소드 선택
        success : function(response){    // 통신 성공 시 응답받은 데이터
            let todoBox = document.querySelector(`#todoBox`);
            let html = response;

            console.log(todoList);
            console.log(response);

            for(let i=0; i<html.length; i++){
                let tno = html[i].tno;
                let e = parseInt(html[i].state);   // X  
                let s = html[i].content;
                
                if(e == 0){                          // e == 'X'는 기본값으로 등록했을 때 화이트 박스가 나오게 하기 위함.
                    html +=`<div id="whiteBox">
                                <span> ${s} </span>
                                <div>
                                    <button type="button" onclick="change(${tno})">변경</button>
                                    <button type="button" onclick="remove(${tno})">삭제</button>
                                </div>
                            </div>`            
                }else{
                    html +=`<div id="blackBox">
                                <span> ${s} </span>
                                <div>
                                    <button type="button" onclick="change(${tno})">변경</button>
                                    <button type="button" onclick="remove(${tno})">삭제</button>
                                </div>
                            </div>`
                }
            }    
            todoBox.innerHTML = html;    
        
        }
    // 2. ajax 메소드에 옵션 넣어서 실행
    }
    $.ajax(option);
}
