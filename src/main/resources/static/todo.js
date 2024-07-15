console.log("todo.js");

// let todoList = ["밥먹기 , X"];  
// 1달에는 JS에서 메모리 관리했지마나 3달에는 웹서버(톰캣 ->DB서버 ) 관리하기 때문에 필요업다.

todoReadAll();

function todoCreate(){
    console.log("todoCreate()");
    // [1] html 입력받은 값 가져오기
    let todoInput = document.querySelector("#todoInput");
    console.log(todoInput);

    let content = todoInput.value;
    console.log(content);

    // [2] AJAX(JQUERY라이브러리) 이용한 웹서버(컨트롤러) 통신해서 요청과 응답하기
    $.ajax({
        url : "/todo/create?content="+content , // HTTP 통신할 경로URL , 컨트롤러 매핑
        method : 'post' ,                // HTTP 메소드
        success : function(response){    // HTTP 성공응답 , 컨트롤러가 return한 값
            console.log(response);      // 응답 결과 확인
            if(response==true){
                alert('리스트 저장 성공');   // 안내 후
                todoInput.value = '';       // 입력상자에 입력된 값 없애기
                todoReadAll();              // 할일 목록 전체 출력 함수 호출
            }else{
                alert('리스트 저장 실패');
            }
        }
    }); // ajax end
    // AJAX는 JQUERY라이브러리 포함된 함수이다. $는 JQUERY라이브러리 문법이다.
}   // todoCreate() end

// 할일 목록 전체 출력 , 실행조건 : 1. JS열렸을 때 , 2. 등록/삭제/수정 (상태변경검사) 성공시
function todoReadAll(){
    console.log("todoReadAll()");
    // - 출력할 데이터 가져오기
    $.ajax({
        method : 'get' , 
        url : '/todo/readall' , 
        success : function(response){
            console.log(response);  // 경과받은 데이터의 타입은 array/list
            // 어디에
            let todoBox = document.querySelector(`#todoBox`);
            // 무엇을
            let html = ``;
            // [1] for(let i = 0; i < response.length;i++){}
            // [2] 리스트명.forEach(반복변수명 => {실행할 코드 })
            response.forEach(todoDto => {
                    html +=`<div id=${todoDto.state == 0? `whiteBox` : `blackBox`}>        
                    <span> ${todoDto.content} </span>
                    <div>
                        <button type="button" onclick="todoUpdate(${todoDto.tno})">변경</button>
                        <button type="button" onclick="todoDelete(${todoDto.tno})">삭제</button>
                    </div>
                    </div>`
            });
            // 출력
            todoBox.innerHTML = html; 
        }
    }); // ajax end
}   // todoReadAll() end

function todoUpdate(tno){
    console.log("todoUpdate()");
    $.ajax({
        // url안에 쿼리스트링 형식으로 데이터 대입
        url : `/todo/update?tno=${tno}` , // HTTP 통신할 경로URL , 컨트롤러 매핑
        method : 'put' ,                // HTTP 메소드
        success : function(response){    // HTTP 성공응답 , 컨트롤러가 return한 값
            console.log(response);
            if(response){
                todoReadAll();  // 새로고침
            }else{
                alert('오류발생:관리자에게문의');
            }
        }
    }); // ajax end
}   // todoUpdate() end

function todoDelete(tno){
    console.log("todoDelete()");
    $.ajax({
        url : "/todo/delete" , // HTTP 통신할 경로URL , 컨트롤러 매핑
        // url 아닌 data 속성에 {key : value} 형식의 데이터 대입
        data : {'tno' : tno} , 
        method : 'delete' ,                // HTTP 메소드
        // [익명함수] success : function(response){}
        // [일반함수] success : function 변수명 (response){}
        // [화살표함수]
        success : response => {    // HTTP 성공응답 , 컨트롤러가 return한 값
            console.log(response);
            if(response){
                todoReadAll();
            }else{
                alert('오류발생:관리자에게문의');
            }
        }
    }); // ajax end
}   //  todoDelete() end