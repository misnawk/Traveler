$(document).ready(function() {
    function updateTotalPrice() {
        let ticketQuantity = $('#ticketQuantity').val();
        let totalPrice = ticket.tickPrice * ticketQuantity;
        $('#totalPrice').text(totalPrice.toLocaleString() + '원');
    }

    $('#ticketQuantity').on('input', updateTotalPrice);

    updateTotalPrice(); // 초기 총 가격 설정

    function reserveTicket() {
        console.log("reserveTicket 함수 시작");

        let isLoggedIn = session.id;
        let ticketNO = ticket.tickNO;
        let ticketPrice = ticket.tickPrice;
        let ticketDate = date;
        let quantity = $('#ticketQuantity').val();

        console.log("isLoggedIn:", isLoggedIn);
        console.log("ticketNO:", ticketNO);
        console.log("ticketPrice:", ticketPrice);
        console.log("ticketDate:", ticketDate);
        console.log("quantity:", quantity);

        let orderData = {
            isLoggedIn: isLoggedIn,
            ticketNO: ticketNO,
            ticketPrice: ticketPrice,
            ticketDate: ticketDate,
            quantity: quantity
        };

        $.ajax({
            url: '/reserveTicket',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(orderData),
            success: function(response) {
                console.log("예약 응답:", response);
                if (!response.loggedIn) {
                    alert("로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.");
                    window.location.href = '/login';
                } else if (response.ordered) {
                    alert('예약이 완료되었습니다.');
                    if (response.diaryCreated) {
                        alert('다이어리에 일정이 추가되었습니다.');
                    }
                } else {
                    alert('예약 처리 중 문제가 발생했습니다: ' + (response.message || '다시 시도해주세요.'));
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

    // 예약하기 버튼에 클릭 이벤트 핸들러 추가
    $(".reserve-btn").click(reserveTicket);
});