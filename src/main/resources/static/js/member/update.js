console.log('update.js');

getMypage();
function getMypage(){
    console.log('getMypage()');
    $.ajax({
        method : 'get' ,
        url : "/member/my/info" ,
        success : (result) => {
            console.log(result);
            if(result == ''){
                alert('로그인 후 이용 가능합니다.');
                location.href="/member/login";
            }
            //
            document.querySelector('.no').innerHTML = `${result.no}`;
            document.querySelector('.id').innerHTML = `${result.id}`;
            document.querySelector('.name').value = `${result.name}`;
            document.querySelector('.phone').value = `${result.phone}`;
            document.querySelector('.email').innerHTML = `${result.email}`;
        }   // success end
    })  // ajax end
}

function doUpdate(){ console.log( 'doUpdate()' )

}


