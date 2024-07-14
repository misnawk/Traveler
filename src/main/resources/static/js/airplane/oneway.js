document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.select-air').forEach(function(button) {
        button.addEventListener('click', function() {
            const airlineNo = this.getAttribute('data-airno');

            if (airlineNo) {
                if (!isLoggedIn) {
                    alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.');
                    window.location.href = loginPageUrl;
                    return;
                }

                // 좌석 선택 페이지로 이동
                window.location.href = `/air/seatShow?airlineNo=${encodeURIComponent(airlineNo)}&tripType=oneway`;
            } else {
                console.error('항공편 번호를 찾을 수 없습니다.');
            }
        });
    });
});