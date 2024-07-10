document.addEventListener('DOMContentLoaded', function() {
  const departureInput = document.getElementById('departure');
  const destinationInput = document.getElementById('destination');
  const departureDateInput = document.getElementById('departure_date');
  const returnDateInput = document.getElementById('return_date');
  const returnDateWrapper = document.getElementById('return_date_wrapper');
  const dropdownContainer = document.querySelector('.dropdown-container');
  const countryList = document.querySelector('.country-list');
  const airportList = document.querySelector('.city-list');
  const tripTypeRadios = document.querySelectorAll('input[name="tripType"]');

  // 국가와 공항 목록 데이터
const countries = [
  { name: '일본', airports: [
    '신치토세공항',
    '주부 센트레아 국제공항',
    '히로시마 공항'
  ]},
  { name: '베트남', airports: [
    '푸바이 국제공항',
    '반돈 국제공항',
    '깜라인 국제공항'
  ]},
  { name: '대만', airports: [
    '타이난 공항',
    '신주 공항',
    '지룽 항구'
  ]},
  { name: '필리핀', airports: [
    '바기오 공항',
    '프란시스코 방고이 국제공항',
    '푸에르토 프린세사 국제공항'
  ]},
  { name: '인도네시아', airports: [
    '자카르타 (수카르노-하타 국제공항)',
    '발리 (응우라 라이 국제공항)',
    '수라바야 (주안다 국제공항)'
  ]},
  { name: '인도', airports: [
    '인디라 간디 국제공항',
    '차트라파티 시바지 국제공항',
    '켐페고우다 국제공항'
  ]},
  { name: '태국', airports: [
    '수완나품 국제공항',
    '돈므앙 국제공항',
    '푸껫 국제공항'
  ]},
  { name: '호주', airports: [
    '시드니 공항',
    '멜버른 공항',
    '브리즈번 공항'
  ]}
];


  // 출발지를 인천국제공항으로 고정
  departureInput.value = '인천국제공항';
  departureInput.readOnly = true;

  // 국가 목록을 채우는 함수
  function populateCountryList() {
    countryList.innerHTML = ''; // 기존 목록 초기화
    countries.forEach(country => {
      const li = document.createElement('li');
      li.textContent = country.name;
      li.addEventListener('click', () => showAirports(country.airports));
      countryList.appendChild(li);
    });
  }

  // 공항 목록을 표시하는 함수
  function showAirports(airports) {
    countryList.style.display = 'none';
    airportList.innerHTML = '';
    airports.forEach(airport => {
      const li = document.createElement('li');
      li.textContent = airport;
      li.addEventListener('click', () => {
        destinationInput.value = airport;
        dropdownContainer.style.display = 'none';
      });
      airportList.appendChild(li);
    });
    airportList.style.display = 'block';
  }

  // 날짜 선택기를 초기화하는 함수
  function initializeDatePickers() {
    $('#departure_date, #return_date').daterangepicker({
      singleDatePicker: true,
      showDropdowns: true,
      minDate: new Date(),
      autoUpdateInput: false,
      locale: {
        format: 'YYYY-MM-DD',
        cancelLabel: 'Clear'
      }
    }).on('apply.daterangepicker', function(ev, picker) {
      $(this).val(picker.startDate.format('YYYY-MM-DD'));
    }).on('cancel.daterangepicker', function(ev, picker) {
      $(this).val('');
    });
  }

  // 도착지 입력 필드 클릭 시 국가 목록 토글
  destinationInput.addEventListener('click', (event) => {
    event.stopPropagation();
    dropdownContainer.style.display = 'block';
    countryList.style.display = 'block';
    airportList.style.display = 'none';
  });

  // 문서 클릭 시 드롭다운 숨기기
  document.addEventListener('click', (e) => {
    if (!destinationInput.contains(e.target) && !dropdownContainer.contains(e.target)) {
      dropdownContainer.style.display = 'none';
    }
  });

  // 왕복/편도 선택에 따른 도착 날짜 필드 표시/숨기기 함수
  function toggleReturnDate() {
    const isOneWay = document.querySelector('input[name="tripType"]:checked').value === 'oneway';
    returnDateWrapper.style.display = isOneWay ? 'none' : 'block';
    returnDateInput.required = !isOneWay;
  }

  // 라디오 버튼에 이벤트 리스너 추가
  tripTypeRadios.forEach(radio => {
    radio.addEventListener('change', toggleReturnDate);
  });

  // 초기화
  populateCountryList();
  initializeDatePickers();
  toggleReturnDate(); // 페이지 로드 시 초기 상태 설정

  // 초기에 드롭다운 컨테이너와 국가 목록을 숨깁니다.
  dropdownContainer.style.display = 'none';
  countryList.style.display = 'none';
});