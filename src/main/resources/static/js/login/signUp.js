let idSccess = false
let pwRangeSccess = false
let pwSccess = false
let red = "#ff0000"
let blue = "#1273E4"

// 생년월일 select
$(document).ready(
    function () {
        let now = new Date();
        let year = now.getFullYear();

        for (var i = 1900; i <= year; i++) {
            $('#year').append('<option th:value="' + i + '">' + i + '</option>');
        }
        for (var i = 1; i < 13; i++) {
            $('#month').append('<option th:value="' + i + '">' + i + '</option>');
        }
        for (var i = 1; i < 32; i++) {
            $('#day').append('<option th:value="' + i + '">' + i + '</option>');
        }
    }
);

// 아이디 중복체크
function idCheck(){
    let id = document.getElementById("userId").value

    if(id == ''){
        alert("아이디를 입력하세요")
        return
    }

    $.ajax({
        url : '/user/check',
        data : {
            id : id
        },
        type : 'POST',
        dataType : 'json',
        success : function(result) {
            if (result == true) {
                alert("사용 가능한 ID입니다")
                idSccess = true
            } else {
                alert("사용이 불가능한 ID입니다")
                document.getElementById("userId").value = ''
                idSccess = false
                return
            }
        }
    });
}

// 비밀번호 설정 범위 확인
function pwRange(){
    let pw = document.getElementById("userPw")
    let pwRange = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()])[a-zA-Z\d!@#$%^&*()]{8,12}$/
    let rangeMsg = document.getElementById("rangeMsg")

    setTimeout(() => {
        if(!pwRange.test(pw.value)){
            rangeMsg.style.color = red;
            rangeMsg.innerHTML = "영문, 숫자, 특수문자 8~12자 사이로 조합해주세요"
            pwRangeSccess = false
        } else {
            rangeMsg.innerHTML = null
            pwRangeSccess = true
        }
    }, 0);
}

// 실시간 비밀번호 확인
function pwCheck(){
   let pw = document.getElementById("userPw")
   let pwCheck = document.getElementById("checkPw")
   let checkMsg = document.getElementById("checkMsg")

   // setTimeout() : 지정된 시간 후 스크립트가 실행되게 하는 함수
   setTimeout(() => {
       if(pw.value == pwCheck.value){
            checkMsg.style.color = blue;
            checkMsg.innerHTML = "비밀번호 일치"
            pwSccess = true
       } else {
            checkMsg.style.color = red;
            checkMsg.innerHTML = "비밀번호 불일치"
            pwSccess = false
       }
   }, 0);
}

// 회원가입
function save(){
    let tell = document.getElementById("userTell").value
    let email = document.getElementById("userEmail").value
    let name = document.getElementById("userName").value
    let year = document.getElementById("year").value
    let month = document.getElementById("month").value
    let day = document.getElementById("day").value

    let idMsg = document.getElementById("idMsg")
    let rangeMsg = document.getElementById("rangeMsg")
    let checkMsg = document.getElementById("checkMsg")
    let birthMsg = document.getElementById("birthMsg")
    let emailMsg = document.getElementById("emailMsg")

    let $sendBtn = $("input#singUp");

    if(idSccess == false){
//        idMsg.style.color = red;
//        idMsg.innerHTML = "아이디 중복 확인을 해주세요"
        return;
    }

    if(name == ""){
        userMsg.style.color = red;
        userMsg.innerHTML = "이름을 입력해주세요"
        return;
    }

    if(pwRangeSccess == false){
        rangeMsg.style.color = red;
        rangeMsg.innerHTML = "영문, 숫자, 특수문자 8~12자 사이로 조합해주세요"
        document.getElementById("userPw").value = "";
        return;
    }

    if(pwSccess == false){
        checkMsg.style.color = red;
        checkMsg.innerHTML = "비밀번호가 일치하지 않습니다"
        document.getElementById("checkPw").value = "";
        return
    }

    if(year == "출생 연도" || month == "월" || day == "일"){
//        birthMsg.style.color = red;
//        birthMsg.innerHTML = "생년월일을 선택해주세요"
        return;
    }

    if(tell == ""){
        tellMsg.style.color = red;
        tellMsg.innerHTML = "전화번호를 입력해주세요"
        return;
    }

    if(email == ""){
        emailMsg.style.color = red;
        emailMsg.innerHTML = "이메일을 입력해주세요"
        return;
    }

    $sendBtn.on("click", function () {
        $("form[name='f']").submit();
    });
}