function save() {
    const hotelData = {
        hotelName: document.getElementById('hotelName').value,
        hotelPrice: document.getElementById('hotelPrice').value,
        hotelImg: document.getElementById('hotelImg').value,
        hotelImg1: document.getElementById('hotelImg1').value,
        hotelImg2: document.getElementById('hotelImg2').value,
        hotelImg3: document.getElementById('hotelImg3').value,
        hotelImg4: document.getElementById('hotelImg4').value,
        hotelImg5: document.getElementById('hotelImg5').value,
        hotelImg6: document.getElementById('hotelImg6').value,
        hotelText: document.getElementById('hotelText').value,
        hotelFacility: document.getElementById('hotelFacility').value,
        hotelCountry: document.getElementById('hotelCountry').value,
        hotelTime: document.getElementById('hotelTime').value,
        hotelCheck: document.getElementById('hotelCheck').value,
        hotelAddr: document.getElementById('hotelAddr').value,
        hotelDescription: document.getElementById('hotelDescription').value,
        hotelSights: document.getElementById('hotelSights').value,
        hotelTotal: document.getElementById('hotelTotal').value,
        binID: document.getElementById('binID').value,
        binCate: document.getElementById('binCate').value
    };

    const roomData = {
        roomName: document.getElementById('roomName').value,
        roomImg: document.getElementById('roomImg').value,
        roomFacility: document.getElementById('roomFacility').value,
        roomMax: document.getElementById('roomMax').value
    };

    const requestData = {
        hotel: hotelData,
        room: roomData
    };

    fetch('/business/saveHotelAndRoom', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestData)
    })
    .then(response => response.text())
    .then(data => {
        console.log('Success:', data);
    })
    .catch(error => {
        console.error('Error:', error);
    });
}