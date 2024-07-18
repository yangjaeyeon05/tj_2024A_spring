console.log('myinfo.js');

getMypage();
function getMypage(){
    console.log('getMypage()');
    $.ajax({
        method : 'get' ,
        url : "/member/myinfo" ,
        success : (result) => {
            console.log(result);
            // 어디에
            let myinfo = document.querySelector(`#myinfo`);
            // 무엇을
            let html = `<span> 회원번호 : ${result.no}</span> <br/>
                        <span> 아이디 : ${result.id}</span> <br/>
                        <span> 이름 : ${result.name}</span> <br/>
                        <span> 이메일 : ${result.email}</span> <br/>
                        <span> 연락처 : ${result.phone}</span>
                        `;
            myinfo.innerHTML = html;
        }   // success end
    })  // ajax end
}