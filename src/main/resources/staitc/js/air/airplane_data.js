const secend = document.querySelector(".secend");

const result = `


   <h1 class="secend__title">추천 여행지</h1>

        <div class="countryBox">
          <div class="countryBox__title">
            <span>한국 국내선</span>
          </div>

          <div class="countryBox__content">
            <div class="countryBox__content__imgBox">
              <img src="../img/korea_1.png" alt="" />
            </div>

            <div class="countryBox__content__info">
              <div class="countryBox__content__info__text">
                <span>광주</span>

                <span>👉</span>
                <span>제주</span>
                <span>이코노미(왕복)</span>
                <span style="color: red">52000~</span>
              </div>
            </div>
          </div>
        </div>


`;

secend.insertAdjacentHTML("beforeend", result);
