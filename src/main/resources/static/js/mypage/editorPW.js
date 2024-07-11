let nowPWSccess = false
let pwRangeSccess = false
let pwSccess = false
let red = "#ff0000"
let blue = "#1273E4"

// 현재 비밀번호 확인
function nowPWCheck(){
    let id = document.getElementById("id").value
    let pw = document.getElementById("nowPW").value

    if(pw == ''){
        alert("비밀번호를 입력하세요")
        return
    }

    $.ajax({
        url : '/mypage/pw/check',
        data : {
            id : id,
            pw : pw
        },
        type : 'POST',
        dataType : 'json',
        success : function(result) {
            if (result == true) {
                alert("다음 단계로 넘어가주세요")
                nowPWSccess = true
            } else {
                alert("비밀번호를 다시 확인해주세요")
                document.getElementById("nowPW").value = ''
                nowPWSccess = false
                return
            }
        }
    });
}

// 비밀번호 설정 범위 확인
function pwRange(){
    let newPW = document.getElementById("newPW").value
    let pwRange = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()])[a-zA-Z\d!@#$%^&*()]{8,12}$/
    let rangeMsg = document.getElementById("rangeMsg")

    setTimeout(() => {
        if(!pwRange.test(newPW)){
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
   let newPW = document.getElementById("newPW")
   let newPWCheck = document.getElementById("newPWCheck")
   let checkMsg = document.getElementById("checkMsg")

   // setTimeout() : 지정된 시간 후 스크립트가 실행되게 하는 함수
   setTimeout(() => {
       if(newPW.value == newPWCheck.value){
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

// 비밀번호 변경 페이지 - 수정하기 버튼
function pwEditor(){
    let pw = document.getElementById("nowPW").value
    let newPW = document.getElementById("newPW").value

    let nowMsg = document.getElementById("nowMsg")
    let rangeMsg = document.getElementById("rangeMsg")
    let checkMsg = document.getElementById("checkMsg")

    let $sendBtn = $("input#saveBtn");

    if(pw == newPW){
        rangeMsg.style.color = red;
        rangeMsg.innerHTML = "현재 사용중인 비밀번호와 동일합니다"
        return;
    }

    if(nowPWSccess == false){
        nowMsg.style.color = red;
        nowMsg.innerHTML = "비밀번호 확인버튼을 눌러주세요"
        return;
    }

    if(pwRangeSccess == false){
        rangeMsg.style.color = red;
        rangeMsg.innerHTML = "영문, 숫자, 특수문자 8~12자 사이로 조합해주세요"
        return;
    }

    if(pwSccess == false){
        checkMsg.style.color = red;
        checkMsg.innerHTML = "새롭게 설정한 비밀번호가 일치하지 않습니다"
        return;
    }

    $sendBtn.on("click", function () {
        $("form[name='f']").submit();
    });
}


// 비밀번호 변경 페이지 - 취소 버튼
function back(){
    let id = document.getElementById("id").value;
    location.href="/mypage/editor/"+id;
}
