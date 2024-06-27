
document.addEventListener('DOMContentLoaded', function() {
  // header.html 파일을 로드하여 #header-placeholder에 삽입합니다.
  fetch('header.html')
      .then(response => response.text())
      .then(data => {
          document.getElementById('header-placeholder').innerHTML = data;

          // 네비게이션 메뉴 항목에 클릭 이벤트 리스너 추가
          const menuItems = document.querySelectorAll('#menu li');
          menuItems.forEach(item => {
              item.addEventListener('click', function() {
                  // 모든 항목에서 active 클래스 제거
                  menuItems.forEach(i => i.classList.remove('active'));
                  // 클릭된 항목에 active 클래스 추가
                  this.classList.add('active');
              });
          });
      });
});
