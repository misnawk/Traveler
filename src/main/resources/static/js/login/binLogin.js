// 로그인 체크
function binLogin(){
   let id = document.getElementById("userId").value;
   let pw = document.getElementById("userPw").value;

   if(id == ''){
        alert("아이디를 입력해주세요")
        return
   }

   if(pw == ''){
        alert("비밀번호를 입력해주세요")
        return
   }

   $.ajax({
       url : '/bin/login',
       data : {
           id : id,
           pw : pw
       },
       type : 'POST',
       dataType : 'json',
       success : function(result) {
           if (result == true) {
                alert("로그인에 성공했습니다")
                location.href="/";
           } else {
               alert("아이디 또는 비밀번호를 확인해주세요")
               return
           }
       }
   });
}

function binSign(){
    location.href = "/binSignUp"
}

