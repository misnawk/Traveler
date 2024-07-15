function allday(date) {
    var adjustedDate = new Date(date);
    adjustedDate.setDate(adjustedDate.getDate()); // 하루 감소
    return adjustedDate.toISOString().split('T')[0]; // 날짜 부분만 반환
}

function backday(date) {
    var adjustedDate = new Date(date);
    adjustedDate.setDate(adjustedDate.getDate() + 1); // 하루 감소
    return adjustedDate.toISOString().split('T')[0]; // 날짜 부분만 반환
}

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        selectable: true,
        dragScroll: true,
        locale: "ko",
        editable: true,
        droppable: true,
        dayMaxEventRows: true,

        events: function(fetchInfo, successCallback, failureCallback) {
            var userId = $('#id').val();
            if (userId) {
                loadDiaryEntries(userId, successCallback, failureCallback);
            } else {
                console.error('User ID not found');
                failureCallback('User ID not found');
            }
        },
        eventDrop: function(info) {
            alert(info.event.title + "가 " + info.event.start.toISOString().split('T')[0] + "로 이동되었습니다.");
            updateDiaryEntry(info.event);
        },
        eventResize: function(info) {
            alert(info.event.title + "가 " + info.event.end.toISOString().split('T')[0] + "로 연장되었습니다.");
            updateDiaryEntry(info.event);
        },
        eventClick: function(info) {
            if (confirm("일정을 삭제하시겠습니까?")) {
               showEditForm(info.event);
            }
        }
    });

    calendar.render();

    $('#event-form').submit(function(e) {
        e.preventDefault();
        saveDiaryEntry(calendar);
    });


    var showFormBtn = document.getElementById('show-event-form');
    var formContainer = document.getElementById('event-form-container');
    var cancelFormBtn = document.getElementById('cancel-event-form');


    showFormBtn.addEventListener('click', function() {
        formContainer.style.display = 'block';
    });

    cancelFormBtn.addEventListener('click', function() {
        formContainer.style.display = 'none';
    });

    // 폼 외부를 클릭하면 폼이 닫히도록 설정
    window.addEventListener('click', function(event) {
        if (event.target == formContainer) {
            formContainer.style.display = 'none';
        }
    });
});

function loadDiaryEntries(userId, successCallback, failureCallback) {
    $.ajax({
        url: '/diary/entries/' + userId,
        type: 'GET',
        success: function(data) {
            var events = data.map(function(entry) {
                return {
                    id: entry.orderID,
                    title: entry.diaryTitle,
                    start: entry.allDay || allday(entry.goDay), // 시간 정보 제거
                    end: backday(entry.backDay), // 시간 정보 제거
                    color: entry.diaryColor || getRandomColor() // diaryColor null일 경우 랜덤 색상 사용
                };
            });

            successCallback(events);
        },
        error: function(xhr, status, error) {
            console.error('Error loading diary entries:', error);
            failureCallback(error);
        }
    });
}

function saveDiaryEntry(calendar) {
    var userId = $('#id').val();
    if (!userId) {
        console.error('User ID not found');
        return;
    }

    var diaryEntry = {
        userId: userId,
        goDay: $('#start').val(),
        backDay: $('#end').val(),
        diaryTitle: $('#title').val(),
        diaryColor: $('#color').val() // 색상 값을 diaryText로 사용
    };
    console.log("Sending diary entry: ", JSON.stringify(diaryEntry));
    $.ajax({
        url: '/diary',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(diaryEntry),
        success: function(response) {
            alert('일정이 저장되었습니다.');
            calendar.addEvent({
                title: diaryEntry.diaryTitle,
                start: diaryEntry.goDay,
                end: diaryEntry.backDay,
                color: diaryEntry.diaryColor,
                editable: true
            });
            $('#event-form')[0].reset();
            document.getElementById('event-form-container').style.display = 'none'; // 폼 숨기기
        },
        error: function(xhr, status, error) {
            console.error('Error saving diary entry:', error);
        }
    });
}

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}