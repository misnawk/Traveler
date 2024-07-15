let idSccess = false
let pwRangeSccess = false
let pwSccess = false
codeSccess = false
let red = "#ff0000"
let blue = "#1273E4"

// 아이디 중복체크
function idCheck(){
    let id = document.getElementById("binID").value

    if(id == ''){
        alert("아이디를 입력하세요")
        return
    }

    $.ajax({
        url : '/bin/idCheck',
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
                document.getElementById("binID").value = ''
                idSccess = false
                return
            }
        }
    });
}

function codeCheck(){
    let binCode = document.getElementById("binCode").value
    let code = binCode.replace(/[^0-9]/g,"")

    if(code == ''){
        alert("사업자등록번호 입력하세요")
        return
    }

    if(code.length > 10){
        alert("사업자등록번호는 10자를 초과하지 않습니다. 다시 확인 부탁드립니다")
        return
    }

    $.ajax({
        url : '/bin/codeCheck',
        data : {
            code : code
        },
        type : 'POST',
        dataType : 'json',
        success : function(result) {
            if (result == true) {
                alert("사용 가능한 사업자등록번호 입니다")
                codeSccess = true
            } else {
                alert("이미 등록된 사업자등록번호 입니다")
                document.getElementById("binCode").value = ''
                codeSccess = false
                return
            }
        }
    });
}

// 비밀번호 설정 범위 확인
function pwRange(){
    let pw = document.getElementById("binPW")
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
   let pw = document.getElementById("binPW")
   let pwCheck = document.getElementById("checkPW")
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

function save(){
    let tell = document.getElementById("binTell").value
    let email = document.getElementById("binEmail").value
    let name = document.getElementById("binName").value
    let code = document.getElementById("binCode").value

    let idMsg = document.getElementById("idMsg")
    let rangeMsg = document.getElementById("rangeMsg")
    let checkMsg = document.getElementById("checkMsg")
    let nameMsg = document.getElementById("nameMsg")
    let codeMsg = document.getElementById("codeMsg")
    let tellMsg = document.getElementById("tellMsg")
    let emailMsg = document.getElementById("emailMsg")

    let $sendBtn = $("input#singUp");

    if(idSccess == false){
//        idMsg.style.color = red;
//        idMsg.innerHTML = "아이디 중복 확인을 해주세요"
        document.getElementById("binID").value = "";
        return;
    }

    if(pwRangeSccess == false){
        rangeMsg.style.color = red;
        rangeMsg.innerHTML = "영문, 숫자, 특수문자 8~12자 사이로 조합해주세요"
        document.getElementById("binPW").value = "";
        return;
    }

    if(pwSccess == false){
        checkMsg.style.color = red;
        checkMsg.innerHTML = "비밀번호가 일치하지 않습니다"
        document.getElementById("checkPW").value = "";
        return
    }

    if(name == ""){
        nameMsg.style.color = red;
        nameMsg.innerHTML = "이름을 입력해주세요"
        return
    }

    if(tell == ""){
        tellMsg.style.color = red;
        tellMsg.innerHTML = "대표번호를 입력해주세요"
        return
    }

    if(email == ""){
        emailMsg.style.color = red;
        emailMsg.innerHTML = "이메일을 입력해주세요"
        return
    }

    $sendBtn.on("click", function () {
        $("form[name='f']").submit();
    });
}