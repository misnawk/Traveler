document.addEventListener('DOMContentLoaded', function() {
    const seats = document.querySelectorAll('.seat');
    const reserveButton = document.getElementById('reserveButton');
    const reservationData = document.getElementById('reservationData');

    seats.forEach(seat => {
        seat.addEventListener('click', selectSeatHandler);
    });

    reserveButton.addEventListener('click', handleReservation);

    function selectSeatHandler(event) {
        selectSeat(event.currentTarget);
    }

    function selectSeat(seatElement) {
        let selectedSeat = document.querySelector('.selected');
        if (selectedSeat) {
            selectedSeat.classList.remove('selected');
        }
        seatElement.classList.add('selected');
        let seatNumber = seatElement.getAttribute('data-seat');
        reserveButton.disabled = false;

        console.log("현재 사용자의 아이디:", userId);
        console.log("현재 탑승 비행기:", airlineNo);
        console.log('선택된 좌석:', seatNumber);

        let row = seatNumber.charAt(0);
        let column = seatNumber.slice(1);

        let rowDescription;
        switch(row) {
            case 'A': rowDescription = '창가 쪽'; break;
            case 'B': rowDescription = '복도 쪽 (왼쪽)'; break;
            case 'C': rowDescription = '복도 쪽 (오른쪽)'; break;
            case 'D': rowDescription = '창가 쪽'; break;
            default: rowDescription = '알 수 없음';
        }

        console.log(`좌석 위치: ${rowDescription}, ${column}번째 열`);
    }

   function handleReservation() {
       if (!isLoggedIn) {
           alert('로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.');
           window.location.href = loginPageUrl;
       } else {
           const selectedSeat = document.querySelector('.seat.selected');
           if (!selectedSeat) {
               alert('좌석을 선택해주세요.');
               return;
           }

           const data = {
               airlineNo: reservationData.dataset.airlineNo,
               seatNumber: selectedSeat.dataset.seat,
               tripType: reservationData.dataset.tripType
           };

           fetch('/air/seat', {
               method: 'POST',
               headers: {
                   'Content-Type': 'application/json'
               },
               body: JSON.stringify(data)
           })
           .then(response => response.json())
           .then(data => {
               if (data.success) {
                   alert(data.message);
                   selectedSeat.classList.add('reserved');
                   reserveButton.disabled = true;
                    alert(data.message);
                           if (data.redirect) {
                               window.location.href = data.redirect;
                           }
               } else {
                   alert(data.message);
               }
           })
           .catch(error => {
               console.error('Error:', error);
               alert('예약 중 오류가 발생했습니다.');
           });
       }
   }
});