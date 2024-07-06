function binWrite(){
    let binCate = document.getElementById("binCate").value;

    if(binCate == "1"){
        location.href="/binpage/airline";
    } else if(binCate == "2"){
        location.href="/binpage/hotel";
    } else if(binCate == "3"){
        location.href="/binpage/tick";
    } else if(binCate == "4"){
        location.href="/binpage/packge";
    } else {
        alert("카테고리를 선택해주세요.");
        return false; // 카테고리가 선택되지 않았을 경우 페이지 이동을 막습니다.
    }
}
function selectCategory(category) {
    document.getElementById("binCate").value = category;
    // 선택된 버튼의 스타일을 변경하는 로직을 추가할 수 있습니다.
    var buttons = document.querySelectorAll('.category-container input[type="button"]');
    buttons.forEach(function(btn) {
        btn.classList.remove('selected');
    });
    event.target.classList.add('selected');
}