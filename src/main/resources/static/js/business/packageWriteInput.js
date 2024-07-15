document.addEventListener('DOMContentLoaded', function() {
    console.log("DOMContentLoaded 이벤트 발생");
    document.getElementById('packageForm').addEventListener('submit', save);
});

function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toISOString();
}

function save(e) {
    e.preventDefault();
    console.log("save 함수 호출됨");

    const formData = new FormData();

    const packageData = {
        packageTitle: document.getElementById('packageTitle').value,
        packageText: document.getElementById('packageText').value,
        packageMax: document.getElementById('packageMax').value,
        packagePrice: document.getElementById('packagePrice').value,
        startDateTime: document.getElementById('startDateTime').value, // formatDate 제거
        endDateTime: document.getElementById('endDateTime').value, // formatDate 제거
        packageCountry: document.getElementById('packageCountry').value
    };
    formData.append('packageData', JSON.stringify(packageData));

    if (document.getElementById('imageUrl').files.length > 0) {
        formData.append('imageFile', document.getElementById('imageUrl').files[0]);
    }

    for (let [key, value] of formData.entries()) {
        console.log(key + ': ' + value);
    }

    fetch('/savePackage', {
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
            alert('패키지가 성공적으로 등록되었습니다!');
        } else {
            alert('패키지 등록 실패: ' + (result.message || '알 수 없는 오류'));
        }
    })
    .catch(error => {
        console.error('오류:', error);
        alert('패키지 등록 중 오류 발생: ' + error.message);
    });
}