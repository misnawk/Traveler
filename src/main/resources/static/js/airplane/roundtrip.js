document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.select-air').forEach(function(button) {
        button.addEventListener('click', function() {


        console.log('departure(출발지):', departure);
        console.log('destination(도착지):', destination);
        console.log('departureDate(출발시간):', departureDate);
        console.log('returnDate(도착시간):', returnDate);



            const airNo = this.getAttribute('data-airno');
            // 전역 변수가 정의되어 있는지 확인
            if (typeof departure !== 'undefined' && typeof destination !== 'undefined' &&
                typeof departureDate !== 'undefined' && typeof returnDate !== 'undefined') {
                window.location.href = `/air/roundtrip_return?departure=${encodeURIComponent(destination)}&destination=${encodeURIComponent(departure)}&departureDate=${encodeURIComponent(departureDate)}&returnDate=${encodeURIComponent(returnDate)}&selectedAirNo=${encodeURIComponent(airNo)}`;
            } else {
                console.error('Required variables are not defined');

            }
        });
    });
});