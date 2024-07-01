  $(function() {
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

    $('.search_button').on('click', function() {
      // 검색 로직 구현
      console.log('검색 시작');
    });
  });



  $(function() {
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
  });
