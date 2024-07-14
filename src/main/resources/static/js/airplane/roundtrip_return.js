document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.select-air').forEach(function(button) {
        button.addEventListener('click', function() {
            const airlineNo = this.getAttribute('data-airlineNo'); // 'data-airlineNo'로 수정
            window.location.href = `/air/seatShow?airlineNo=${encodeURIComponent(airlineNo)}&tripType=roundtrip`;
        });
    });
});
