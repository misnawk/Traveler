
// 페이지 로드가 왼료시 실행된다.
document.addEventListener('DOMContentLoaded', function() {

    //select-air 클래스를 가진 모든 버튼에 대해서
    document.querySelectorAll('.select-air').forEach(function(button) {
        //클릭이벤트 추가한다.
        button.addEventListener('click', function() {

            //data-no 속성에서 항공편NO 번호 가져오기
            const airlineNo = this.getAttribute('data-no');
            if (airlineNo) {
                //항공편 번호가 있다면
                console.log("항공편 찾음 좌석페이지로 이동함")
                window.location.href = `/seat/selection?airlineNo=${encodeURIComponent(airlineNo)}&tripType=oneway`;
                //좌석 선택페이지로 이동
            } else {
                console.log('항공편NO 못받음ㅅㄱ');
                //항공편 번호가 없으면 에러
            }
        });
    });
});

function selectReturnAir(airNo) {
    const urlParams = new URLSearchParams(window.location.search);
    const departure = urlParams.get('departure');
    const destination = urlParams.get('destination');
    const departureDate = urlParams.get('departureDate');
    const returnDate = urlParams.get('returnDate');
    const selectedDepartureAirNo = urlParams.get('selectedAirNo');

    if (airNo) {
        window.location.href = `/seat/selection?airlineNo=${encodeURIComponent(airlineNo)}&departure=${encodeURIComponent(departure)}&destination=${encodeURIComponent(destination)}&departureDate=${encodeURIComponent(departureDate)}&returnDate=${encodeURIComponent(returnDate)}&tripType=oneway`;
    } else {
        console.error('No airNo found.');
    }
}
