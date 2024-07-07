$(document).ready(function() {
    $('#go-to-payment').click(function() {
        console.log("Button clicked"); // 버튼 클릭 여부 확인

        const hotelNO = $(this).data('hotel-no');
        const checkin = $(this).data('checkin') || '';  // 기본값 설정
        const checkout = $(this).data('checkout') || '';  // 기본값 설정
        const guestCount = $(this).data('guest-count') || 1;  // 기본값 설정

        if (!checkin || !checkout || !guestCount) {
            alert('국가, 날짜, 인원수를 작성해야 합니다. 검색창으로 이동합니다.');
            window.location.href = '/hotel'; // 검색창 URL로 이동
        } else {
            // 로그인이 되어 있는지 확인
            $.ajax({
                url: '/check-login',
                type: 'GET',
                success: function(response) {
                    if (response.loggedIn) {
                        // 모든 정보가 입력되었으면 결제 페이지로 이동
                        window.location.href = `/hotelPayment/${hotelNO}?checkin=${checkin}&checkout=${checkout}&guestCount=${guestCount}`;
                    } else {
                        // 로그인이 되어 있지 않으면 로그인 페이지로 이동
                        alert("로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.");
                        window.location.href = '/login';
                    }
                },
                error: function() {
                    alert('서버와의 통신 중 오류가 발생했습니다.');
                }
            });
        }
    });
});
