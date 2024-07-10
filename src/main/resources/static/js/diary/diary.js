function allday(date) {
    var adjustedDate = new Date(date);
    adjustedDate.setDate(adjustedDate.getDate() + 1); // 하루 감소
    return adjustedDate.toISOString().split('T')[0]; // 날짜 부분만 반환
}
function backday(date) {
    var adjustedDate = new Date(date);
    adjustedDate.setDate(adjustedDate.getDate() + 2); // 하루 감소
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
                deleteDiaryEntry(info.event);
                info.event.remove();
            }
        }
    });

    calendar.render();

    $('#event-form').submit(function(e) {
        e.preventDefault();
        saveDiaryEntry(calendar);
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
                    start: entry.goDay || allday(entry.allDay)  , // 시간 정보 제거
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
        allDay: $('#start').val(),
        backDay: $('#end').val(),
        diaryTitle: $('#title').val(),
        diaryColor: $('#color').val() // 색상 값을 diaryColor 사용
    };

    $.ajax({
        url: '/diary',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(diaryEntry),
        success: function(response) {
            alert('일정이 저장되었습니다.');
            calendar.addEvent({
                id: response.orderID,
                title: diaryEntry.diaryTitle,
                start: diaryEntry.allDay,
                end: diaryEntry.backDay,
                color: diaryEntry.diaryColor,
                editable: true
            });
            $('#event-form')[0].reset();
        },
        error: function(xhr, status, error) {
            console.error('Error saving diary entry:', error);
        }
    });
}

function updateDiaryEntry(event) {
    var diaryEntry = {
        orderID: event.id,
        allDay: event.start.toISOString().split('T')[0],
        backDay: event.end ? event.end.toISOString().split('T')[0] : null,
        diaryTitle: event.title,
        diaryColor: event.backgroundColor
    };

    $.ajax({
        url: '/diary/' + event.id,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(diaryEntry),
        success: function(response) {
            alert('일정이 업데이트되었습니다.');
        },
        error: function(xhr, status, error) {
            console.error('Error updating diary entry:', error);
        }
    });
}

function deleteDiaryEntry(event) {
    $.ajax({
        url: '/diary/' + event.id,
        type: 'DELETE',
        success: function(response) {
            alert('일정이 삭제되었습니다.');
        },
        error: function(xhr, status, error) {
            console.error('Error deleting diary entry:', error);
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



