$(document).ready(function() {
    function reserveTicket() {
        console.log("reserveTicket 함수 시작");
        $.ajax({
            url: '/check-login',
            type: 'GET',
            success: function(response) {
                console.log("Login check response:", response);
                if (response.loggedIn === true) {
                    console.log("로그인 성공, proceedWithReservation 호출");
                    // 로그인된 경우, 티켓 예약 진행
                    proceedWithReservation();
                } else {
                    console.log("로그인되지 않음");
                    // 로그인되지 않은 경우
                    alert("로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.");
                    window.location.href = '/login';
                }
            },
            error: function(xhr, status, error) {
                console.error("Error checking login status:", error);
                alert('서버와의 통신 중 오류가 발생했습니다.');
            }
        });
    }

    function proceedWithReservation() {
        console.log("proceedWithReservation 함수 시작");
        var orderData = {
            comNO: ticketTickNO,
            totalCnt: $('#ticketQuantity').val(),
            // 필요한 다른 데이터 추가
        };

        $.ajax({
            url: '/ticket/order',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(orderData),
            success: function(response) {
                console.log("예약 응답:", response);
                if (response.success) {
                    alert('예약이 완료되었습니다.');
                } else {
                    alert('예약 처리 중 문제가 발생했습니다. 다시 시도해주세요.');
                }
            },
            error: function(xhr, status, error) {
                console.error("예약 중 오류 발생:", error);
                console.error("Status:", status);
                console.error("Response:", xhr.responseText);
                alert('서버와의 통신 중 오류가 발생했습니다.');
            }
        });
    }

    // 결제하기 버튼에 클릭 이벤트 핸들러 추가
    $(".reserve-btn").click(function() {
        reserveTicket();
    });
});
