document.addEventListener('DOMContentLoaded', function() {
    const quantityInput = document.getElementById('ticketQuantity');
    const totalPriceSpan = document.getElementById('totalPrice');

    quantityInput.addEventListener('change', updateTotalPrice);

    function updateTotalPrice() {
        const quantity = parseInt(quantityInput.value);
        const totalPrice = ticketPrice * quantity;
        totalPriceSpan.textContent = totalPrice.toLocaleString() + '원';
    }

    // 초기 총 가격 설정
    updateTotalPrice();
});

function payTicket() {
    if (!isLoggedIn) {
        alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.');
        window.location.href = '/login';
    } else {
        const quantity = parseInt(document.getElementById('ticketQuantity').value);
        $.ajax({
            url: '/reserveTicket',
            type: 'POST',
            data: {
                tickNO: ticketTickNO,
                quantity: quantity
            },
            success: function(response) {
                if (response.loggedIn) {
                    if (response.reserved) {
                        alert('예약이 완료되었습니다.');
                    } else {
                        alert('예약 처리 중 문제가 발생했습니다. 다시 시도해주세요.');
                    }
                } else {
                    alert('세션이 만료되었습니다. 다시 로그인해주세요.');
                    window.location.href = '/login';
                }
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
                alert('예약 처리 중 오류가 발생했습니다.');
            }
        });
    }
}