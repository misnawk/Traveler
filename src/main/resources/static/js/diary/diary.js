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
        }, eventClick: function(info) {
                       showEditForm(info.event);
            }

    });

    calendar.render();

    $('#event-form').submit(function(e) {
        e.preventDefault();
        saveDiaryEntry(calendar);
    });

    var editFormContainer = document.getElementById('edit-event-form-container');
    var cancelEditFormBtn = document.getElementById('cancel-edit-event-form');
    var deleteEventBtn = document.getElementById('delete-event');

    var showFormBtn = document.getElementById('show-event-form');
    var formContainer = document.getElementById('event-form-container');
    var cancelFormBtn = document.getElementById('cancel-event-form');
    cancelEditFormBtn.addEventListener('click', function() {
        editFormContainer.style.display = 'none';
    });

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
    deleteEventBtn.addEventListener('click', function() {
        if (confirm("정말로 이 일정을 삭제하시겠습니까?")) {
            deleteDiaryEntry(calendar);
        }
    });

        $('#edit-event-form').submit(function(e) {
            e.preventDefault();
            updateDiaryEntry(calendar);
        });

        // 폼 외부를 클릭하면 폼이 닫히도록 설정
        window.addEventListener('click', function(event) {
            if (event.target == editFormContainer) {
                editFormContainer.style.display = 'none';
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
                    color: entry.diaryColor || getRandomColor(), // diaryColor null일 경우 랜덤 색상 사용
                    extendedProps: {
                       diaryNO: entry.diaryNO // 이 부분이 올바르게 설정되어 있는지 확인
                    }
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
function showEditForm(event) {
    var editFormContainer = document.getElementById('edit-event-form-container');
    document.getElementById('edit-event-id').value = event.extendedProps.diaryNO;  // diaryNO 사용
    document.getElementById('edit-title').value = event.title;
    document.getElementById('edit-start').value = event.start.toISOString().split('T')[0];
    document.getElementById('edit-end').value = event.end ? event.end.toISOString().split('T')[0] : '';
    document.getElementById('edit-color').value = event.backgroundColor;
    editFormContainer.style.display = 'block';
}

function updateDiaryEntry(calendar) {
    var diaryNO = document.getElementById('edit-event-id').value;
    var diaryEntry = {
        diaryNO: diaryNO,
        userId: $('#id').val(),
        goDay: $('#edit-start').val(),
        backDay: $('#edit-end').val(),
        diaryTitle: $('#edit-title').val(),
        diaryColor: $('#edit-color').val()
    };

    $.ajax({
        url: '/diary/' + diaryNO,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(diaryEntry),
        success: function(response) {
            alert('일정이 수정되었습니다.');
            var event = calendar.getEventById(diaryNO);
            if (event) {
                event.remove();
            }
            calendar.addEvent({
                id: diaryNO,
                title: diaryEntry.diaryTitle,
                start: diaryEntry.goDay,
                end: diaryEntry.backDay,
                color: diaryEntry.diaryColor,
                editable: true,
                extendedProps: {
                    diaryNO: diaryNO
                }
            });
            document.getElementById('edit-event-form-container').style.display = 'none';
        },
        error: function(xhr, status, error) {
            console.error('Error updating diary entry:', error);
        }
    });
}

function deleteDiaryEntry(calendar) {
    var diaryNO = document.getElementById('edit-event-id').value;
        console.log("Attempting to delete diary entry with diaryNO:", diaryNO);
    $.ajax({
        url: '/diary/delete/' + diaryNO, // URL 수정: '/delete' 앞에 '/diary'를 추가하고 '/' 추가
        type: 'DELETE',
        success: function(response) {
            console.log("Delete success:", response);
            alert('일정이 삭제되었습니다.');
            var event = calendar.getEventById(diaryNO);
            if (event) {
                event.remove();
            }
            document.getElementById('edit-event-form-container').style.display = 'none';
        },
        error: function(xhr, status, error) {
            console.error('Error deleting diary entry:', error);
            console.error('Status:', status);
            console.error('Response:', xhr.responseText);
            console.error('Error deleting diary entry:', error);
            alert('일정 삭제 중 오류가 발생했습니다: ' + xhr.responseText);  // 서버에서 보낸 오류 메시지 표시
        }
    });
}

