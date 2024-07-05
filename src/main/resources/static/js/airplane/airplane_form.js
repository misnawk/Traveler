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
      { name: '지룽 항구', code: 'JIL' }
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

  // 출발지를 인천국제공항으로 고정
  departureInput.value = '인천국제공항';
  departureInput.readOnly = true;

  // 국가 목록을 채우는 함수
  function populateCountryList() {
    countries.forEach(country => {
      const li = document.createElement('li');
      li.textContent = country.name;
      // 국가 클릭 시 해당 국가의 도시 목록 표시
      li.addEventListener('click', () => showCities(country.cities));
      countryList.appendChild(li);
    });
  }

  // 도시 목록을 표시하는 함수
  function showCities(cities) {
    // 국가 목록 숨기기
    countryList.classList.add('hidden');
    cityList.innerHTML = '';
    cities.forEach(city => {
      const li = document.createElement('li');
      li.textContent = `${city.name} (${city.code})`;
      // 도시 클릭 시 도착지 입력 필드에 도시 이름 설정
      li.addEventListener('click', () => {
        destinationInput.value = city.name;
        cityList.classList.add('hidden');
      });
      cityList.appendChild(li);
    });
    // 도시 목록 표시
    cityList.classList.remove('hidden');
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
    });

    // 날짜 선택 시 입력 필드에 설정
    $('#departure_date, #return_date').on('apply.daterangepicker', function(ev, picker) {
      $(this).val(picker.startDate.format('YYYY-MM-DD'));
    });

    // 날짜 초기화 시 입력 필드 비우기
    $('#departure_date, #return_date').on('cancel.daterangepicker', function(ev, picker) {
      $(this).val('');
    });
  }

  // 왕복/편도 선택에 따라 도착일 필드를 표시/숨기는 함수
  function handleTripTypeChange() {
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
  }

  // 도착지 입력 필드 클릭 시 국가 목록 토글
  destinationInput.addEventListener('click', () => {
    countryList.classList.toggle('hidden');
    cityList.classList.add('hidden');
  });

  // 문서 클릭 시 드롭다운 리스트 숨기기
  document.addEventListener('click', function(e) {
    if (!destinationInput.contains(e.target) && !countryList.contains(e.target) && !cityList.contains(e.target)) {
      countryList.classList.add('hidden');
      cityList.classList.add('hidden');
    }
  });

  // 검색 버튼 클릭 이벤트
  document.addEventListener('DOMContentLoaded', function() {
      const tripTypeInputs = document.querySelectorAll('input[name="tripType"]');
      const returnDateInput = document.getElementById('return_date');

      tripTypeInputs.forEach(input => {
          input.addEventListener('change', function() {
              if (this.value === 'oneway') {
                  returnDateInput.disabled = true;
              } else {
                  returnDateInput.disabled = false;
              }
          });
      });

      const departureInput = document.getElementById('departure');
      const destinationInput = document.getElementById('destination');
      const countryList = document.querySelector('.country_list');
      const cityList = document.querySelector('.city_list');

      if (departureInput && destinationInput && countryList && cityList) {
          departureInput.value = '인천국제공항';
          departureInput.readOnly = true;

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
              // Add other countries and cities here
          ];

          function populateCountryList() {
              countries.forEach(country => {
                  const li = document.createElement('li');
                  li.textContent = country.name;
                  li.addEventListener('click', () => showCities(country.cities));
                  countryList.appendChild(li);
              });
          }

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

          populateCountryList();

          destinationInput.addEventListener('click', () => {
              countryList.classList.toggle('hidden');
              cityList.classList.add('hidden');
          });

          document.addEventListener('click', function(e) {
              if (!destinationInput.contains(e.target) && !countryList.contains(e.target) && !cityList.contains(e.target)) {
                  countryList.classList.add('hidden');
                  cityList.classList.add('hidden');
              }
          });
      }
  });
