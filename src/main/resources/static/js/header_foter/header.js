document.addEventListener("DOMContentLoaded", function() {
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
