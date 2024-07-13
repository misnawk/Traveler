

document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.select-air').forEach(function(button) {
        button.addEventListener('click', function() {
            const airNo = this.getAttribute('data-airno');
            window.location.href = `/seat/selection?airNo=${encodeURIComponent(airNo)}&tripType=oneway`;
        });
    });
});

function selectReturnAir(airNo) {
    const urlParams = new URLSearchParams(window.location.search);
    const departure = urlParams.get('departure');
    const destination = urlParams.get('destination');
    const departureDate = urlParams.get('departureDate');
    const returnDate = urlParams.get('returnDate');
    const selectedDepartureAirNo = urlParams.get('airNo');

    window.location.href = `/seat/selection?airNo=${encodeURIComponent(airNo)}&departure=${encodeURIComponent(departure)}&destination=${encodeURIComponent(destination)}&departureDate=${encodeURIComponent(departureDate)}&returnDate=${encodeURIComponent(returnDate)}&tripType=roundtrip`;
}


