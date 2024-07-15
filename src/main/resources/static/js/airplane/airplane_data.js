const recommendedTravel = [
  {
    region: "한국",
    departures: ["인천국제공항", "인천국제공항", "인천국제공항"],
    destinations: ["제주", "광주", "여수"],
//    flightClass: "이코노미",
    tripType: ["왕복", "왕복", "왕복"],
    prices: [50200, 102200, 106200],
    imgUrl: "http://www.laborplus.co.kr/news/photo/200807/1768_729_5947.gif"
  },
  {
    region: "도쿄",
    departures: ["인천국제공항", "인천국제공항", "인천국제공항"],
    destinations: ["홍콩", "오사카", "타이베이"],

    tripType: ["편도", "왕복", "편도"],
    prices: [294600, 304500, 309700],
    imgUrl: "https://www.agoda.com/wp-content/uploads/2019/01/Things-to-do-in-Tokyo-Sensoji-Temple.jpg"
  },
  {
    region: "베트남",
    departures: ["인천국제공항", "인천국제공항", "인천국제공항"],
    destinations: ["방콕", "마닐라", "다낭"],
//    flightClass: "이코노미",
    tripType: ["왕복", "왕복", "편도"],
    prices: [433600, 340600, 437900],
    imgUrl: "https://media.triple.guide/triple-cms/c_limit,f_auto,h_1024,w_1024/98b9911b-cd4b-4318-a124-ed4b26404779.jpeg"
  }
];
document.addEventListener('DOMContentLoaded', function() {
  const secondSection = document.querySelector('.second');

  recommendedTravel.forEach(travel => {
    const travelCard = document.createElement('div');
    travelCard.className = 'travel-card';

    travelCard.innerHTML = `
      <img src="${travel.imgUrl}" alt="${travel.region}" class="travel-image">
      <div class="travel-info">
        <h2>${travel.region}</h2>
        <div class="travel-details">
          ${travel.departures.map((dep, index) => `
            <div class="travel-route">
              <p>${dep} → ${travel.destinations[index]}</p>
              <p>${travel.tripType[index]}</p>
              <p class="price">${travel.prices[index].toLocaleString()}원~</p>
            </div>
          `).join('')}
        </div>
      </div>
    `;

   secondSection.appendChild(travelCard);
  });
});