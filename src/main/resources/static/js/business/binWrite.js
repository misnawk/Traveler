function saveHotel() {
    var hotelData = {
        hotelName: $('#hotelName').val(),
        hotelPrice: $('#hotelPrice').val(),
        hotelImg: $('#hotelImg').val(),
        hotelImg1: $('#hotelImg1').val(),
        hotelImg2: $('#hotelImg2').val(),
        hotelImg3: $('#hotelImg3').val(),
        hotelImg4: $('#hotelImg4').val(),
        hotelImg5: $('#hotelImg5').val(),
        hotelImg6: $('#hotelImg6').val(),
        hotelText: $('#hotelText').val(),
        hotelFacility: $('#hotelFacility').val(),
        hotelCountry: $('#hotelCountry').val(),
        hotelTime: $('#hotelTime').val(),
        hotelCheck: $('#hotelCheck').val(),
        hotelAddr: $('#hotelAddr').val(),
        hotelDescription: $('#hotelDescription').val(),
        hotelSights: $('#hotelSights').val(),
        hotelTotal: $('#hotelTotal').val(),
        binID: $('#binID').val(),
        binCate: $('#binCate').val()
    };

    $.ajax({
        type: 'POST',
        url: '/business/saveHotel',
        data: JSON.stringify(hotelData),
        contentType: 'application/json',
        success: function(response) {
            if (response.status === 'success') {
                $('#hotelNO').val(response.hotelNO); // 호텔 번호를 설정
            } else {
                alert('데이터 저장 오류: ' + response.message);
            }
        },
        error: function(xhr, status, error) {
            alert('데이터 저장 오류: ' + error);
        }
    });
}

function back() {
    window.history.back();
}