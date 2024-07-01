document.addEventListener('DOMContentLoaded', function() {
  const departureInput = document.getElementById('departure');
  const destinationInput = document.getElementById('destination');
  const departureDateInput = document.getElementById('departure_date');
  const returnDateInput = document.getElementById('return_date');
  const passengersInput = document.getElementById('passengers');
  const countryList = document.querySelector('.country_list');
  const cityList = document.querySelector('.city_list');

  const countries = [
    { name: '일본', cities: [
      { name: '신치토세 공항', code: 'CTS' },
      { name: '주부 센트레아 국제공항', code: 'NGO' },
      { name: '히로시마 공항', code: 'HIJ' }
    ]},
    { name: '베트남', cities: [
      { name: '푸바이 국제공항', code: 'HUI' },
      { name: '반돈 국제공항', code: 'VDO' },
      { name: '깜라인 국제공항', code: 'CXR' }
    ]},
    { name: '대만', cities: [
      { name: '타이난 공항', code: 'TNN' },
      { name: '신주 공항', code: 'HSZ' },
      { name: '지룽 항구', code: 'JIL' } // 공항 없음, 항구로 대체
    ]},
    { name: '싱가포르', cities: [
      { name: '창이 국제공항', code: 'SIN' }
    ]},
    { name: '코타키나발루', cities: [
      { name: '산다칸 공항', code: 'SDK' },
      { name: '라하드 다투 공항', code: 'LDU' },
      { name: '타와우 공항', code: 'TWU' }
    ]},
    { name: '태국', cities: [
      { name: '우타파오 국제공항', code: 'UTP' },
      { name: '매팟 국제공항', code: 'CEI' },
      { name: '후아힌 공항', code: 'HHQ' }
    ]},
    { name: '필리핀', cities: [
      { name: '바기오 공항', code: 'BAG' },
      { name: '프란시스코 방고이 국제공항', code: 'DVO' },
      { name: '푸에르토 프린세사 국제공항', code: 'PPS' }
    ]},
    { name: '몽골', cities: [
      { name: '다르항 공항', code: 'DAH' },
      { name: '에르데네트 공항', code: 'ERT' },
      { name: '촐로트솜 공항', code: 'HLH' }
    ]}
  ];

  // 출발지를 김포국제공항으로 고정
  departureInput.value = '김포국제공항';
  departureInput.readOnly = true;

  // 나라 목록 생성
  countries.forEach(country => {
    const li = document.createElement('li');
    li.textContent = country.name;
    li.addEventListener('click', () => showCities(country.cities));
    countryList.appendChild(li);
  });

  // 도시 목록 표시
  function showCities(cities) {
    countryList.classList.add('hidden');
    cityList.innerHTML = '';
    cities.forEach(city => {
      const li = document.createElement('li');
      li.textContent = `${city.name} (${city.code})`;
      li.addEventListener('click', () => {
        destinationInput.value = city.name;
        cityList.classList.add('hidden');
      });
      cityList.appendChild(li);
    });
    cityList.classList.remove('hidden');
  }

  // 도착지 입력 필드 클릭 이벤트
  destinationInput.addEventListener('click', () => {
    countryList.classList.toggle('hidden');
    cityList.classList.add('hidden');
  });

  // 문서 클릭 이벤트 추가 (드롭다운 외부 클릭 시 닫기)
  document.addEventListener('click', function(e) {
    if (!destinationInput.contains(e.target) && !countryList.contains(e.target) && !cityList.contains(e.target)) {
      countryList.classList.add('hidden');
      cityList.classList.add('hidden');
    }
  });

  // 달력 기능 (daterangepicker 사용)
  $('#departure_date, #return_date').daterangepicker({
    singleDatePicker: true,
    showDropdowns: true,
    minDate: new Date(),
    autoUpdateInput: false,
    locale: {
      format: 'YYYY-MM-DD',
      cancelLabel: 'Clear'
    }
  });

  // 날짜 선택 이벤트
  $('#departure_date, #return_date').on('apply.daterangepicker', function(ev, picker) {
    $(this).val(picker.startDate.format('YYYY-MM-DD'));
  });

  // 날짜 초기화 이벤트
  $('#departure_date, #return_date').on('cancel.daterangepicker', function(ev, picker) {
    $(this).val('');
  });

  // 왕복/편도 선택에 따른 도착일 필드 표시/숨김
  const tripTypeInputs = document.querySelectorAll('input[name="trip_type"]');
  tripTypeInputs.forEach(input => {
    input.addEventListener('change', (e) => {
      if (e.target.value === 'oneway') {
        returnDateInput.parentElement.style.display = 'none';
      } else {
        returnDateInput.parentElement.style.display = 'block';
      }
    });
  });

  // 검색 버튼 클릭 이벤트
  document.querySelector('#airSearchForm').addEventListener('submit', function(e) {
    e.preventDefault(); // 기본 제출 동작 방지

    // 필수 필드 검증
    const destination = document.getElementById('destination').value;
    const departureDate = document.getElementById('departure_date').value;
    const returnDate = document.getElementById('return_date').value;
    const passengers = document.getElementById('passengers').value;

    if (!destination || !departureDate || !passengers) {
      alert('목적지, 출발일, 인원 수를 모두 입력해주세요.');
      return;
    }

    // 왕복 여행인 경우 돌아오는 날짜 확인
    const tripType = document.querySelector('input[name="trip_type"]:checked').value;
    if (tripType === 'roundtrip' && !returnDate) {
      alert('왕복 여행의 경우 돌아오는 날짜를 입력해주세요.');
      return;
    }

    // 폼 제출
    this.submit();
  });

  // 인원수 입력 검증
  document.getElementById('airSearchForm').addEventListener('submit', function(e) {
    const passengersInput = document.getElementById('passengers');
    const passengers = parseInt(passengersInput.value);

    if (isNaN(passengers) || passengers < 1 || passengers > 10) {
      e.preventDefault();
      alert('인원수는 1명에서 10명 사이여야 합니다.');
      passengersInput.focus();
    }
  });
});