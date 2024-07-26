console.log('getall.js');
getall()
function getall(){
    let list = [];
    
    $.ajax({
        async : false , // 동기화 vs true 비동기화 (innerHTML 후에 작동)
        method:'get',
        url:"/board/all",
        success : (r) =>{
            console.log(r);
            list = r;
        } , 
        error : (e)=>{
            console.log(e);
        }
    })  // ajax end
    let board=document.querySelector('#list');
    let html='';
    list.forEach(result =>{
        console.log(result);
        html+=`<tr>
                <th>${result.bno}</th>
                <td><a href="/board/getread?bno=${result.bno}">${result.btitle}</a></td>
                <td>${result.id}</td>
                <td>${result.bdate}</td>
                <td>${result.bview}</td>
                </tr>`;
    });
    board.innerHTML=html;

}   // getall() end

// 보통 수업에서 출력을 success 안에 넣음 
// 밖으로 빼고 싶으면 list 변수에 r 을 넣어주고
// ajax를 동기화로 바꿔줘야한다.
