function payTicket() {
    if (!isLoggedIn) {
        alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.');
        window.location.href = '/login';
    } else {
        $.ajax({
            url: '/reserveTicket',
            type: 'POST',
            data: { binID: ticketBindId },
            success: function(response) {
                if (response.loggedIn) {
                    if (response.reserved) {
                        alert('결제가 완료되었습니다.');
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
}