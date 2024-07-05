function logout(){
    if(confirm("로그아웃 하시겠습니까?") == true){
        location.href="/logout";
    } else {
        return false;
    }
}

function adminPage(){
    let id = document.getElementById("adminID").value;

    if(id != 'admin'){
        alert("접근 권한이 없습니다");
        return;
    }

    location.href="/admin";
}