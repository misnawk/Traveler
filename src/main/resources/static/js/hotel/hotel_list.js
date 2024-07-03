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
                var option = this.id == "checkin" ? "minDate" : "maxDate",
                    instance = $(this).data("datepicker"),
                    date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate, instance.settings);
                $('#checkin, #checkout').not(this).datepicker("option", option, date);
            }
        });

        // 체크인 달력
        $("#checkin").datepicker({
            minDate: 0, // 오늘 이후의 날짜만 선택 가능
            onSelect: function(selectedDate) {
                var nextDay = new Date(selectedDate);
                nextDay.setDate(nextDay.getDate() + 1);
                $("#checkout").datepicker("option", "minDate", nextDay);
            }
        });

        // 체크아웃 달력
        $("#checkout").datepicker({
            minDate: 1 // 내일 이후의 날짜만 선택 가능
        });

        // 검색 버튼 클릭 이벤트
        $('.search_button').on('click', function() {
            if (validateForm()) {
                // 검색 로직 구현
                console.log('검색 시작');
            }
        });

        // 유효성 검사
        function validateForm() {
            const destination = $('#destination').val();
            const checkin = $('#checkin').val();
            const checkout = $('#checkout').val();

            if (!destination) {
                alert('나라/도시를 입력해주세요');
                return false;
            }

            if (!checkin) {
                alert('체크인 날짜를 선택해주세요');
                return false;
            }

            if (!checkout) {
                alert('체크아웃 날짜를 선택해주세요');
                return false;
            }

            return true;
        }

        $('#hotelForm').on('submit', function(event){
            event.preventDefault();
            const destination = $('#destination').val();
            const checkin = $('#checkin').val();
            const checkout = $('#checkout').val();

            if(destination && checkin && checkout){
                console.log(`${destination}를 입력함`);
                const encodedDestination = encodeURIComponent(destination);
                window.location.href = `/hotel/place/${encodedDestination}`;
            }
        });
    });