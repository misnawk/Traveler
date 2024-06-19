
$(function () {
  var currentYear = new Date().getFullYear();
  var maxYear = currentYear + 3;
  var today = new Date();

  $("#startDay").daterangepicker({
    singleDatePicker: true,
    showDropdowns: true,
    autoUpdateInput: false,
    minYear: currentYear,
    maxYear: maxYear,
    minDate: today,
    locale: {
      format: "YYYY.MM.DD",
      daysOfWeek: ["일", "월", "화", "수", "목", "금", "토"],
      monthNames: [
        "1월",
        "2월",
        "3월",
        "4월",
        "5월",
        "6월",
        "7월",
        "8월",
        "9월",
        "10월",
        "11월",
        "12월",
      ],
      cancelLabel: "취소",
      applyLabel: "확인",
      customRangeLabel: "사용자 지정",
      firstDay: 0,
    },
    parentEl: "#startDayContainer",
    opens: "center",
    drops: "down",
  });

  $("#startDay").on("apply.daterangepicker", function (ev, picker) {
    $("#startDay").val(picker.startDate.format("YYYY.MM.DD"));
    $("#startCompleteBtn").remove(); // 완료 버튼 제거

    // 출발일을 선택하면 도착일의 최소 날짜를 설정
    $("#endDay").data("daterangepicker").setStartDate(picker.startDate);
    $("#endDay").data("daterangepicker").minDate = picker.startDate;
  });

  $("#startDay").on("show.daterangepicker", function (ev, picker) {
    setTimeout(function () {
      if ($("#startCompleteBtn").length === 0) {
        $("#startDayContainer .daterangepicker").append(
          '<button id="startCompleteBtn" class="complete-btn">완료</button>'
        );
        $("#startCompleteBtn").on("click", function () {
          picker.clickApply();
        });
      }
    }, 10);
  });

  $("#endDay").daterangepicker({
    singleDatePicker: true,
    showDropdowns: true,
    autoUpdateInput: false,
    minYear: currentYear,
    maxYear: maxYear,
    minDate: today,
    locale: {
      format: "YYYY.MM.DD",
      daysOfWeek: ["일", "월", "화", "수", "목", "금", "토"],
      monthNames: [
        "1월",
        "2월",
        "3월",
        "4월",
        "5월",
        "6월",
        "7월",
        "8월",
        "9월",
        "10월",
        "11월",
        "12월",
      ],
      cancelLabel: "취소",
      applyLabel: "확인",
      customRangeLabel: "사용자 지정",
      firstDay: 0,
    },
    parentEl: "#endDayContainer",
    opens: "center",
    drops: "down",
  });

  $("#endDay").on("apply.daterangepicker", function (ev, picker) {
    $("#endDay").val(picker.startDate.format("YYYY.MM.DD"));
    $("#endCompleteBtn").remove(); // 완료 버튼 제거
  });

  $("#endDay").on("show.daterangepicker", function (ev, picker) {
    setTimeout(function () {
      if ($("#endCompleteBtn").length === 0) {
        $("#endDayContainer .daterangepicker").append(
          '<button id="endCompleteBtn" class="complete-btn">완료</button>'
        );
        $("#endCompleteBtn").on("click", function () {
          picker.clickApply();
        });
      }
    }, 10);
  });
});









