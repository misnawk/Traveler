$(document).ready(function() {
    $('.method').click(function() {
        $(this).find('input[type="radio"]').prop('checked', true);
    });

    $('#payment-button').click(function() {
        const hotelNO = $(this).data('hotel-no');
        const checkin = $(this).data('checkin');
        const checkout = $(this).data('checkout');
        const guestCount = $(this).data('guest-count');
        const orderDate = new Date().toISOString().slice(0, 10);

        if (!hotelNO || !checkin || !checkout || !guestCount) {
            alert('예약 정보가 올바르지 않습니다. 날짜, 인원수 등을 입력해주세요.');
            window.location.href = '/hotel';
            return;
        }

        $.ajax({
            url: '/check-login',
            type: 'GET',
            success: function(response) {
                if (!response.loggedIn) {
                    alert("로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.");
                    window.location.href = '/login';
                } else {
                    processPayment(hotelNO, checkin, checkout, guestCount, orderDate);
                }
            },
            error: function() {
                alert('서버와의 통신 중 오류가 발생했습니다.');
            }
        });
    });

    function processPayment(hotelNO, checkin, checkout, guestCount, orderDate) {
        $.ajax({
            url: '/hotel/order',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                hotelNO: hotelNO,
                checkin: checkin,
                checkout: checkout,
                guestCount: guestCount,
                orderDate: orderDate
            }),
            success: function(response) {
                if (response.loggedIn) {
                    if (response.success) {
                        alert('결제가 완료되었습니다.');
                        window.location.href = '/payment/success';
                    } else {
                        alert('결제 처리 중 문제가 발생했습니다. 다시 시도해주세요.');
                    }
                } else {
                    alert('세션이 만료되었습니다. 다시 로그인해주세요.');
                    window.location.href = '/login';
                }
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
                alert('결제 처리 중 오류가 발생했습니다.');
            }
        });
    }
});
