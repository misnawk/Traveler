document.addEventListener('DOMContentLoaded', function() {
    var cityButtons = document.querySelectorAll('.city_btn');

    cityButtons.forEach(function(cityButton) {
        cityButton.addEventListener('click', function() {
            var cityNO = cityButton.getAttribute('data-cityno');
            if (cityNO) {
                var newUrl = '/city?cityNO=' + cityNO;
                window.location.href = newUrl;
            } else {
                console.error('City number is missing');
            }
        });
    });
});

document.addE

