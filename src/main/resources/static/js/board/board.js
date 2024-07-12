    function showLoginAlert() {
    alert("로그인을 해주세요.");
    // 선택적: 로그인 페이지로 리다이렉트
    // window.location.href = '/login';
}

function filterBoard(tripType, button) {
    const rows = document.querySelectorAll('#board_list tr');
    rows.forEach(row => {
        const typeCell = row.querySelector('td:nth-child(1)');
        const typeText = typeCell.innerText;
        if ((tripType === 1 && typeText === '패키지') || (tripType === 0 && typeText === '자유여행')) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });

    // 버튼 스타일 변경
    const buttons = document.querySelectorAll('.filter-container button');
    buttons.forEach(btn => btn.classList.remove('active'));
    button.classList.add('active');


}
     function filterBoard(tripType) {
            const url = new URL(window.location.href);
            url.searchParams.set('tripType', tripType);
            window.location.href = url.toString();
    }

