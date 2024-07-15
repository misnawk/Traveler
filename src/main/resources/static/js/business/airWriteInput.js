// 전역 스코프에 updateCities 함수 정의
function updateCities() {

    console.log("updateCities 함수 호출됨");
    let country = document.getElementById("arrivalCountry").value;
    console.log("선택된 국가:", country);
    let citySelect = document.getElementById("arrivalCity");
    citySelect.innerHTML = '<option value="">도시 선택</option>';

    const cities = {
        '100': [
            {code: '101', name: '도쿄'},
            {code: '102', name: '오사카'},
            {code: '103', name: '삿포로'}
        ],
        '200': [
            {code: '201', name: '호찌민'},
            {code: '202', name: '하노이'},
            {code: '203', name: '다낭'}
        ],
        '300': [
            {code: '301', name: '타이베이'},
            {code: '302', name: '가요슝'},
            {code: '303', name: '타이중'}
        ],
        '400': [
            {code: '401', name: '세부'},
            {code: '402', name: '보홀'},
            {code: '403', name: '보라카이'}
        ],
        '500': [
            {code: '501', name: '발리'},
            {code: '502', name: '자카르타'},
            {code: '503', name: '빈탄섬'}
        ],
        '600': [
            {code: '601', name: '뉴델리'},
            {code: '602', name: '뭄바이'},
            {code: '603', name: '벵갈루루'}
        ],
        '700': [
            {code: '701', name: '방콕'},
            {code: '702', name: '치앙마이'},
            {code: '703', name: '푸껫'}
        ],
        '800': [
            {code: '801', name: '시드니'},
            {code: '802', name: '멜버른'},
            {code: '803', name: '브리즈번'}
        ]
    };

    if (cities[country]) {
        cities[country].forEach(function(city) {
            let option = document.createElement("option");
            option.value = city.code;
            option.text = city.name;
            citySelect.add(option);
        });
    }
}

function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toISOString();
}

function save() {
    const citySelect = document.getElementById('arrivalCity');
    const selectedCity = citySelect.options[citySelect.selectedIndex];

    const data = {
        airTitle: document.getElementById('airTitle').value,
        departureAirport: document.getElementById('departureAirport').value,
        arrivalAirport: selectedCity.text,  // 선택된 도시 이름
        cityNO: selectedCity.value,  // 선택된 도시 코드
        airCompany: document.getElementById('airCompany').value,
        departureDateTime: formatDate(document.getElementById('departureDateTime').value),
        arrivalDateTime: formatDate(document.getElementById('arrivalDateTime').value),
        airPrice: document.getElementById('airPrice').value
    };

    // 데이터 유효성 검사
    for (let key in data) {
        if (!data[key]) {
            alert(`${key} 필드를 채워주세요.`);
            return;
        }
    }


    fetch('/saveAirline', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => { throw new Error(text) });
        }
        return response.json();
    })
    .then(result => {
        if (result.success) {
            alert('데이터가 성공적으로 저장되었습니다!');
        } else {
            alert('데이터 저장 실패: ' + (result.message || '알 수 없는 오류'));
        }
    })
    .catch(error => {
        console.error('오류:', error);
        alert('데이터 저장 중 오류 발생: ' + error.message);
    });
}

document.addEventListener('DOMContentLoaded', function() {
    console.log("DOMContentLoaded 이벤트 발생");
    document.getElementById('arrivalCountry').addEventListener('change', updateCities);
    document.getElementById('saveBtn').addEventListener('click', save);
});