$(document).ready(function() {
    $('.method').click(function() {
        $(this).find('input[type="radio"]').prop('checked', true);
    });

    $('#payment-button').click(function() {
            const hotelNO = $(this).data('hotel-no');
            const guestCount = $(this).data('guest-count');

            if (confirm('결제하시겠습니까?')) {
                $.ajax({
                    url: `/hotel/${hotelNO}/order`,
                    type: 'POST',
                    data: {
                        peopleCount: guestCount
                    },
                    success: function(response) {
                        if (response.orderId) {
                            alert('결제가 완료되었습니다. 주문번호: ' + response.orderId);
                            // window.location.href = '/reservation-confirmation/' + response.orderId; // 페이지 이동 필요 없음
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
    });