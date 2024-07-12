$(document).ready(function() {
    console.log("Document ready 이벤트 시작");

    const orderButton = $('#orderButton');
    const form = $('#orderForm');

    orderButton.on('click', function(e) {
        e.preventDefault();
        console.log("결제 버튼 클릭");

        const peopleCount = $('#peopleCount').val();
        const packageNo = form.data('package-no');

        console.log("패키지 번호:", packageNo);
        console.log("인원 수:", peopleCount);

        $.ajax({
            url: `/packages/${packageNO}/order`,
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            data: `peopleCount=${peopleCount}`,
            success: function(data) {
                console.log("서버 응답:", data);
                if (data.orderId) {
                    alert('결제가 완료되었습니다.');
                } else if (data.error) {
                    alert(data.error);
                } else {
                    alert('결제 처리 중 오류가 발생했습니다.');
                }
            },
            error: function(xhr, status, error) {
                console.error('오류 발생:', error);
                alert('결제 처리 중 오류가 발생했습니다.');
            }
        });
    });
});
