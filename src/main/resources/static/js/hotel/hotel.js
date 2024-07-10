$(document).ready(function() {
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

            // 체크아웃 날짜가 체크인 날짜보다 이전이면 체크아웃 날짜를 다음날로 설정
            var checkoutDate = $("#checkout").datepicker("getDate");
            if (checkoutDate <= new Date(selectedDate)) {
                $("#checkout").datepicker("setDate", nextDay);
            }
        }
    });

    // 체크아웃 달력 설정
    $("#checkout").datepicker({
        minDate: 1,
        onSelect: function(selectedDate) {
            var prevDay = new Date(selectedDate);
            prevDay.setDate(prevDay.getDate() - 1);
            $("#checkin").datepicker("option", "maxDate", prevDay);
        }
    });

    // 유효성 검사 함수
    function validateForm() {
        console.log("validateForm 함수 실행");

        const destination = $('#destination').val();
        console.log("destination:", destination);

        const checkin = $('#checkin').val();
        const checkout = $('#checkout').val();

        if (!destination) {
            console.log("destination이 비어있음, alert 표시");
            alert('나라명을 입력해주세요');
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

        // 체크아웃 날짜가 체크인 날짜 이후인지 확인
        const checkinDate = new Date(checkin);
        const checkoutDate = new Date(checkout);
        if (checkoutDate <= checkinDate) {
            alert('체크아웃 날짜는 체크인 날짜 다음날부터 선택 가능합니다.');
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