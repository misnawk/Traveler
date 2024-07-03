function processPayment() {
    const confirmPayment = confirm('결제하시겠습니까?');
    if (confirmPayment) {
        alert('결제가 완료되었습니다.');
        // window.location.href = '/payment/success';  // 결제 성공 페이지로 이동 (필요에 따라 주석 해제)
    }
}
