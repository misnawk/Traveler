function selectDepartureAir(airNo) {
    const urlParams = new URLSearchParams(window.location.search);
    const departure = encodeURIComponent(urlParams.get('departure'));
    const destination = encodeURIComponent(urlParams.get('destination'));
    const departureDate = urlParams.get('departureDate');
    const returnDate = urlParams.get('returnDate');
    window.location.href = `/air/roundtrip_return?departure=${departure}&destination=${destination}&departureDate=${departureDate}&returnDate=${returnDate}&selectedAirNo=${encodeURIComponent(airNo)}`;
}


//document.addEventListener('DOMContentLoaded', function() {
//    document.querySelectorAll('.select-air').forEach(function(button) {
//        button.addEventListener('click', function() {
//            const airNo = this.getAttribute('data-airno');
//            const returnDate = /*[[${returnDate}]]*/ '';
//            window.location.href = `/air/roundtrip_return?departure=${encodeURIComponent(/*[[${destination}]]*/)}&destination=${encodeURIComponent(/*[[${departure}]]*/)}&departureDate=${encodeURIComponent(/*[[${departureDate}]]*/)}&returnDate=${encodeURIComponent(returnDate)}&selectedAirNo=${encodeURIComponent(airNo)}`;
//        });
//    });
//});
