
document.addEventListener('DOMContentLoaded', function() {
    // header.html 파일을 로드하여 #header-placeholder에 삽입합니다.
    fetch('foter.html')
        .then(response => response.text())
        .then(data => {
            document.getElementById('foter-placeholder').innerHTML = data;
  
        }) 
  });
  