document.addEventListener('DOMContentLoaded', function() {
    const orderButton = document.getElementById('orderButton');
    const form = document.getElementById('orderForm');

    orderButton.addEventListener('click', function(e) {
        e.preventDefault();

        const peopleCount = document.getElementById('peopleCount').value;
        const packageNo = form.getAttribute('data-package-no');

        fetch(`/packages/${packageNo}/order`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `peopleCount=${peopleCount}`
        })
        .then(response => response.json())
        .then(data => {
            if (data.orderId) {
                alert('결제가 완료되었습니다.');
            } else if (data.error) {
                alert(data.error);
            } else {
                alert('결제 처리 중 오류가 발생했습니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('결제 처리 중 오류가 발생했습니다.');
        });
    });
});
