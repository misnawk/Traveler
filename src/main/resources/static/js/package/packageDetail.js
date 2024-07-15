$(document).ready(function() {
    function updateTotalPrice() {
        let peopleCount = $('#peopleCount').val();
        let totalPrice = packageData.packagePrice * peopleCount;
        $('#totalPrice').text(totalPrice.toLocaleString() + '원');
    }

    $('#peopleCount').on('input', updateTotalPrice);

    updateTotalPrice(); // 초기 총 가격 설정

    $('#reserveButton').click(function() {
        let peopleCount = $('#peopleCount').val();

        $.ajax({
            url: `/packages/${packageData.packageNO}/order`,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                peopleCount: peopleCount,
                startDateTime: packageData.startDateTime,
                endDateTime: packageData.endDateTime
            }),
            success: function(response) {
                console.log("예약 응답:", response);
                if (!response.loggedIn) {
                    alert("로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.");
                    window.location.href = '/login';
                } else if (response.ordered) {
                    alert('예약이 완료되었습니다. 주문 ID: ' + response.orderId);
                    if (response.diaryCreated) {
                        alert('다이어리에 일정이 추가되었습니다.');
                    }
                } else {
                    alert('예약 처리 중 문제가 발생했습니다: ' + (response.error || '다시 시도해주세요.'));
                }
            },
            error: function(xhr, status, error) {
                console.error("예약 중 오류 발생:", error);
                console.error("Status:", status);
                console.error("Response:", xhr.responseText);
                alert('서버와의 통신 중 오류가 발생했습니다.');
            }
        });
    });
});