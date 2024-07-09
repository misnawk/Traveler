// 비밀번호 변경 페이지
function pwEditor(){
    let id = document.getElementById("binID").value;
    location.href="/binpage/pw/"+id;
}

// 정보 변경 페이지 - 수정 버튼
function save(){
    let binName = document.getElementById("binName").value;
    let binTell = document.getElementById("binTell").value;
    let binEmail = document.getElementById("binEmail").value;

    let $sendBtn = $("input#saveBtn");

    if(binName == null){
        alert("상호명을 입력해주세요");
        return
    }

    if(binTell == null){
        alert("대표전화번호를 입력해주세요");
        return
    }

    if(binEmail == null){
        alert("대표이메일을 입력해주세요")
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

// 정보 변경 페이지 - 취소 버튼
function HomeBack(){
    location.href="/binpage/"+id;
}