console.log('getall.js');
getall()
function getall(){
    let board=document.querySelector('#list');
    let html='';
    $.ajax({
        method:'get',
        url:"/board/all",
        success:result =>{
            result.forEach(result =>{
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
        }
    })

}
