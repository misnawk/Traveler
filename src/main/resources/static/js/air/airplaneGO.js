const main = document.querySelector("main");

const result = `

 <div class="flight-container">
        <div class="airline-info">
          <img
            src="../../img/image.png"
            alt="Airline Logo"
            class="airline-logo"
          />
          <div class="flight-details">
            <div>일본항공</div>
            <div>JL 5246, 수하물포함</div>
            <a href="#" class="details-btn">상세일정 보기</a>
          </div>

          <div>
            <span>출발: 09:00</span>
          </div>
        </div>
        <div class="duration-container">
          <div class="duration">11시간 5분</div>
          <div class="timeline">
            <div class="line"><i class="fa-solid fa-arrow-right-long"></i></div>
            <div class="stop">KIX</div>
            <div class="layover">경유 1회</div>
          </div>
        </div>
        <div>
          <span>도착: 09:00</span>
        </div>
        <div class="flight-price">
          <div>790,700 원~</div>
          <a href="#">JADE Classic 하나카드 외 8건 / 성인1인</a>
          <div class="note">잔여 9석</div>
        </div>
        <button class="select-btn">선택</button>
      </div>

`;

main.insertAdjacentHTML("beforeend", result);
