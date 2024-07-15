function binWrite(){
    let binCate = document.getElementById("binCate").value;

    // Correctly log the binCate value to the console
    console.log(binCate);

    if(binCate == "1"){
        location.href="/binpage/airline";
    } else if(binCate == "2"){
        location.href="/binpage/hotel";
    } else if(binCate == "3"){
        location.href="/binpage/tick";
    } else if(binCate == "4"){
        location.href="/binpage/package";
    }
}

//  내 정보 수정하기 페이지로 이동
function proEditor(){
    let id = document.getElementById("binID").value;
    location.href="/binpage/editor/"+id;
}

// 수정한 정보 저장
function save(){
    let binName = document.getElementById("binName").value;
    let binTell = document.getElementById("binTell").value;
    let binEmail = document.getElementById("binEmail").value;

    let $sendBtn = $("input#saveBtn");

    if(binName == null){
        alert("상호명을 입력해주세요")
        return
    }
    if(binTell == null){
        alert("대표번호를 입력해주세요")
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

// 비밀번호 변경 페이지
function pwEditor(){
    let id = document.getElementById("binID").value;
    location.href="/binpage/pw/"+id;
}


// 내 정보 변경 페이지 - 취소 버튼
function HomeBack(){
    let id = document.getElementById("binID").value
    location.href="/binpage/"+id;
}

