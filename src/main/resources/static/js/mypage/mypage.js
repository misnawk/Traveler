function home(){
    location.href="/"
}

//  내 정보 수정하기 페이지로 이동
function proEditor(){
    let id = document.getElementById("id").value;
    location.href="/mypage/editor/"+id;
}
