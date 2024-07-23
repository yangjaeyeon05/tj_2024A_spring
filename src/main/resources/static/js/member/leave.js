console.log('leave.js');

function doLeave(){
    console.log('doLeave()');
    // 1. 
    let pwConfirm = document.querySelector(".pwConfirm").value;
    console.log(pwConfirm);
    $.ajax({
        method : 'delete' , 
        url : "/member/leave" , 
        data : { pwConfirm : pwConfirm } , 
        success : (result) =>{
            console.log(result);
            if(result){
                alert('탈퇴성공');
                location.href="/";
            }else{
                alert('회원정보가 일치하지 않습니다.')
            }
        } ,    // success end
        error : (e)=>{
            console.log(e);
        }
    }); // ajax end 
}   // doLeave() end