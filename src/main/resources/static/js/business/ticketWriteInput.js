document.addEventListener('DOMContentLoaded', function() {
    console.log("DOMContentLoaded 이벤트 발생");
    document.getElementById('ticketForm').addEventListener('submit', save);
});

function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toISOString();
}

function save(e) {
    e.preventDefault();
    console.log("save 함수 호출됨");

    const formData = new FormData();

    // 티켓 데이터를 JSON으로 변환하여 추가
    const ticketData = {
        tickTitle: document.getElementById('tickTitle').value,
        tickText: document.getElementById('tickText').value,
        tickOp: document.getElementById('tickOp').value,
        tickPrice: document.getElementById('tickPrice').value,
        tickDate: formatDate(document.getElementById('tickDate').value),
        tickPlace: document.getElementById('tickPlace').value,
        category: document.getElementById('category').value
    };
   formData.append('ticketData', JSON.stringify(ticketData));

    // 이미지 파일 추가
    if (document.getElementById('tickImg').files.length > 0) {
        formData.append('tickImg', document.getElementById('tickImg').files[0]);
    }

    // 데이터 로깅
    for (let [key, value] of formData.entries()) {
        console.log(key + ': ' + value);
    }

    fetch('/saveTicket', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => { throw new Error(text) });
        }
        return response.json();
    })
    .then(result => {
        if (result.success) {
            alert('티켓이 성공적으로 등록되었습니다!');
        } else {
            alert('티켓 등록 실패: ' + (result.message || '알 수 없는 오류'));
        }
    })
    .catch(error => {
        console.error('오류:', error);
        alert('티켓 등록 중 오류 발생: ' + error.message);
    });
}
