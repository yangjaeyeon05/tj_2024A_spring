console.log('product.js');

// 1. 모든 제품 호출 함수
getProductFindAll();
function getProductFindAll(){
    console.log('getProductFindAll()');

    // 어디에
    const productListBox = document.querySelector('.productListBox');
    // 무엇을
    let html = ``;
    $.ajax({    // - 데이터 요청 ajax
        async : false , 
        method : "get" , 
        url : "/product/find/all" , 
        success : (r) => {
            console.log(r);
            // 여러개 제품 . r : 여러개 제품들 리스트 product : 각 제품 , fileNames : 각 제품들의 이미지들 리스트 , imgName : 각 이미지 1개
            r.forEach( product => {
                console.log(product);
                html += `<div class="productBox">`;    // 제품 1개당 div하나씩
                // 여러개 이미지 파일 , 제품마다의 여러개 이미지들
                product.fileNames.forEach(imgName => {
                    console.log(imgName);
                    // 업로드된 파일명을 서버로 요청을 해서 응답받아 img 마크업 src속성에 대입. /업로드된폴더명/파일명
                    html += `<img style="width:100px;" src="/upload/${imgName}" />`;
                });
                html += `</div>`;   // 제품 1개당 div end
            })   // 여러개 제품 반복문 end
        } , 
        error : (e) => {
            console.log(e);
        }
    })  // ajax end
    // 출력
    productListBox.innerHTML = html;
}   // getProductFindAll() end