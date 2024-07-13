$(document).ready(function() {
    $('.method').click(function() {
        $(this).find('input[type="radio"]').prop('checked', true);
    });

    $('#payment-button').click(function() {
        const hotelNO = $(this).data('hotel-no');
        const guestCount = $(this).data('guest-count');
        const checkin = $(this).data('checkin');
        const checkout = $(this).data('checkout');
        const hotelName = $(this).data('hotel-name');
        const hotelText = $(this).data('hotel-text');
        const hotelTime = $(this).data('hotel-time');

        console.log('hotelNO:', hotelNO);
        console.log('guestCount:', guestCount);
        console.log('checkin:', checkin);
        console.log('checkout:', checkout);
        console.log('hotelName:', hotelName);
        console.log('hotelText:', hotelText);
        console.log('hotelTime:', hotelTime);

        // 체크인 시간 추출
        const checkInTime = extractCheckInTime(hotelTime);
        console.log('checkInTime:', checkInTime);

        if (confirm('결제하시겠습니까?')) {
            $.ajax({
                url: `/hotel/${hotelNO}/order`,
                type: 'POST',
                data: {
                    binCate: "2", // 또는 다른 값으로 설정
                    peopleCount: guestCount,
                    useDate: checkin // checkin 날짜를 useDate로 전달
                },
                success: function(response) {
                    console.log('Order Response:', response);
                    if (response.orderId) {
                        alert('결제가 완료되었습니다. 주문번호: ' + response.orderId);

                        // 결제 완료 후 다이어리 생성
                        createDiary(response.orderId, hotelNO, checkin, checkout, hotelName, hotelText, checkInTime);
                    } else {
                        alert('결제 처리 중 문제가 발생했습니다.');
                    }
                },
                error: function(xhr, status, error) {
                    console.error('Error:', xhr.responseText);
                    alert('결제 처리 중 오류가 발생했습니다: ' + xhr.responseText);
                }
            });
        }
    });

    function createDiary(orderId, hotelNO, checkin, checkout, hotelName, hotelText, checkInTime) {
        $.ajax({
            url: `/hotel/${hotelNO}/diary`,
            type: 'POST',
            data: {
                orderId: orderId,
                goday: checkin,
                backday: checkout,
                hotelName: hotelName,
                hotelText: hotelText,
                checkInTime: checkInTime
            },
            success: function(response) {
                console.log('Diary Response:', response);
                if (response.success) {
                    console.log('다이어리가 생성되었습니다.');
                    alert('여행 다이어리가 생성되었습니다.');
                    window.location.href = "/hotel";
                } else {
                    console.error('다이어리 생성 중 문제가 발생했습니다.');
                    alert('다이어리 생성 중 문제가 발생했습니다.');
                }
            },
            error: function(xhr, status, error) {
                console.error('다이어리 생성 중 오류가 발생했습니다:', xhr.responseText);
                alert('다이어리 생성 중 오류가 발생했습니다. 나중에 다시 시도해주세요.');
            }
        });
    }

    function extractCheckInTime(hotelTime) {
        const checkInMatch = hotelTime.match(/체크인\s*(\d{2}:\d{2})/);
        if (checkInMatch) {
            return checkInMatch[1];
        } else {
            console.error('체크인 시간을 찾을 수 없습니다:', hotelTime);
            return null;
        }
    }
});
