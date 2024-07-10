let selectedSeat = null;

function createSeatMap() {
    const seatContainer = document.querySelector('.seats-container');
    const rows = Math.max(...seats.map(seat => parseInt(seat.seatNumber.slice(0, -1))));
    const cols = ['A', 'B', 'C', 'D', 'E', 'F'];

    for (let row = 1; row <= rows; row++) {
        const rowElement = document.createElement('div');
        rowElement.className = 'row';

        for (let i = 0; i < cols.length; i++) {
            if (i === 3) {
                // 통로 추가
                const aisle = document.createElement('div');
                aisle.className = 'aisle';
                rowElement.appendChild(aisle);
            }

            const col = cols[i];
            const seatNumber = `${row}${col}`;
            const seatInfo = seats.find(s => s.seatNumber === seatNumber);

            const seatElement = document.createElement('div');
            seatElement.className = 'seat';
            seatElement.textContent = seatNumber;

            if (seatInfo) {
                seatElement.dataset.seatId = seatInfo.seatID;
                seatElement.dataset.seatNumber = seatInfo.seatNumber;

                if (!seatInfo.isAvailable) {
                    seatElement.classList.add('unavailable');
                } else {
                    seatElement.addEventListener('click', function() {
                        selectSeat(this);
                    });
                }
            } else {
                seatElement.classList.add('unavailable');
            }

            rowElement.appendChild(seatElement);
        }

        seatContainer.appendChild(rowElement);
    }
}

function selectSeat(seatElement) {
    if (selectedSeat) {
        selectedSeat.classList.remove('selected');
    }
    selectedSeat = seatElement;
    selectedSeat.classList.add('selected');
}

function reserveSeat() {
    if (!selectedSeat) {
        alert('좌석을 선택해주세요.');
        return;
    }

    const seatNumber = selectedSeat.dataset.seatNumber;

    fetch('/seat/reserve', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `airlineNo=${airlineNo}&seatNumber=${seatNumber}`
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('HTTP status ' + response.status);
        }
        return response.json();
    })
    .then(data => {
        alert('좌석이 성공적으로 예약되었습니다.');
        selectedSeat.classList.remove('selected');
        selectedSeat.classList.add('unavailable');
        selectedSeat.removeEventListener('click', selectSeat);
        selectedSeat = null;
    })
    .catch(error => {
        console.error('Error:', error);
        if (error.message.includes('409')) {
            alert('이미 예약된 좌석입니다. 다른 좌석을 선택해주세요.');
        } else {
            alert('좌석 예약 중 오류가 발생했습니다.');
        }
    });
}

createSeatMap();

document.getElementById('reserveButton').addEventListener('click', reserveSeat);