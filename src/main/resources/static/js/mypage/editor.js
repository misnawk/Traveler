// 비밀번호 변경 페이지
function pwEditor(){
    let id = document.getElementById("id").value;
    location.href="/mypage/pw/"+id;
}

// 내 정보 변경 페이지 - 수정 버튼
function save(){
    let userName = document.getElementById("userName").value;
    let userTell = document.getElementById("userTell").value;
    let userEmail = document.getElementById("userEmail").value;

    let $sendBtn = $("input#saveBtn");

    if(userName == null){
        alert("이름을 입력해주세요");
        return
    }

    if(userTell == null){
        alert("전화번호를 입력해주세요");
        return
    }

    if(userEmail == null){
        alert("이메일을 입력해주세요")
        return
    }

    if(confirm("정보를 수정하시겠습니까?") == true){
        $sendBtn.on("click", function () {
            $("form[name='f']").submit();
        });
    } else {
        return false;
    }
}

// 내 정보 변경 페이지 - 취소 버튼
function HomeBack(){
    location.href="/mypage/"+id;
}
