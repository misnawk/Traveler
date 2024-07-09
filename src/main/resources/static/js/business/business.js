// 기업 정보 수정하기 페이지로 이동
function proEditor(){
    let id = document.getElementById("id").value;
    location.href="/binpage/editor/"+id;
}

// binCate에 따라 작성 페이지로 이동
function binWrite(){
    let binCate = document.getElementById("binCate").value;

    if(binCate == "1"){
        location.href="/binpage/airline";
    } else if(binCate == "2"){
        location.href="/binpage/hotel";
    } else if(binCate == "3"){
        location.href="/binpage/tick";
    } else if(binCate == "4"){
        location.href="/binpage/packge";
    }

}