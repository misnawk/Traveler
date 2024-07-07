$(document).ready(function() {
    // 한국어 설정
    $.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: '년'
    });

    // 체크인 및 체크아웃 달력 설정
    $("#checkin, #checkout").datepicker({
        dateFormat: 'yy-mm-dd',
        minDate: 0,
        onSelect: function(selectedDate) {
            var option = this.id === "checkin" ? "minDate" : "maxDate",
                instance = $(this).data("datepicker"),
                date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
            $('#checkin, #checkout').not(this).datepicker("option", option, date);
        }
    });

    // 체크인 달력 설정
    $("#checkin").datepicker({
        minDate: 0,
        onSelect: function(selectedDate) {
            var nextDay = new Date(selectedDate);
            nextDay.setDate(nextDay.getDate() + 1);
            $("#checkout").datepicker("option", "minDate", nextDay);
        }
    });

    // 체크아웃 달력 설정
    $("#checkout").datepicker({
        minDate: 1
    });

    // 유효성 검사 함수
    function validateForm() {
        const destination = $('#destination').val();
        const checkin = $('#checkin').val();
        const checkout = $('#checkout').val();

        if (!destination) {
            alert('나라/도시를 입력해주세요');
            return false;
        }

        if (!checkin) {
            alert('가는날 날짜를 선택해주세요');
            return false;
        }

        if (!checkout) {
            alert('오는날 날짜를 선택해주세요');
            return false;
        }

        return true;
    }

    // 폼 제출 이벤트 핸들러
    $('#hotelForm').on('submit', function(event){
        event.preventDefault();
        const destination = $('#destination').val();
        const checkin = $('#checkin').val();
        const checkout = $('#checkout').val();
        const guestCount = $('#guestCount').val();

        if (validateForm()) {
            const encodedDestination = encodeURIComponent(destination);
            const searchParams = new URLSearchParams({
                checkin: checkin,
                checkout: checkout,
                guestCount: guestCount
            }).toString();
            window.location.href = `/hotel/country/${encodedDestination}?${searchParams}`;
        }
    });

    $('#hotel_detail').on('click', function(event) {
            event.preventDefault();
            const hotelNO = $(this).data('hotel-no');
            const checkin = $(this).data('checkin') || '';  // Default to empty string if undefined
            const checkout = $(this).data('checkout') || '';  // Default to empty string if undefined
            const guestCount = $(this).data('guest-count') || 1;  // Default to 1 if undefined

            window.location.href = `/hotelDetail/${hotelNO}?checkin=${checkin}&checkout=${checkout}&guestCount=${guestCount}`;
        });


});