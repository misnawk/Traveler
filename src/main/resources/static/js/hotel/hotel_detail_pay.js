$(document).ready(function() {
    $('#go-to-payment').click(function() {
        console.log("Button clicked"); // 버튼 클릭 여부 확인

        const hotelNO = $(this).data('hotel-no');
        const checkin = $(this).data('checkin') || '';
        const checkout = $(this).data('checkout') || '';
        const guestCount = $(this).data('guest-count') || 1;

        if (!checkin || !checkout || !guestCount) {
            alert('여행지, 날짜, 인원수를 작성해야 합니다. 검색창으로 이동합니다.');
            window.location.href = '/hotel';
        } else {
            $.ajax({
                url: '/check-login',
                type: 'GET',
                dataType: 'json',
                xhrFields: {
                    withCredentials: true
                },
                success: function(response) {
                    console.log('Login check response:', response);
                    if (response && response.loggedIn === true) {
                        // 로그인 되어 있으면 결제 페이지로 이동
                        window.location.href = `/hotelPayment/${hotelNO}?checkin=${checkin}&checkout=${checkout}&guestCount=${guestCount}`;
                    } else {
                        // 로그인 되어 있지 않으면 로그인 페이지로 이동
                        alert("로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.");
                        window.location.href = '/login';
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.error('Error details:', jqXHR, textStatus, errorThrown);
                    alert('서버와의 통신 중 오류가 발생했습니다. 상세 내용: ' + textStatus);
                }
            });
        }
    });
});