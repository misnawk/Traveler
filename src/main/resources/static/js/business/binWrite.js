function save() {
            if (isFormValid()) {
                let hotelData = {
                    hotelName: document.getElementById("hotelName").value,
                    hotelPrice: document.getElementById("hotelPrice").value,
                    hotelImg: document.getElementById("hotelImg").value,
                    hotelImg1: document.getElementById("hotelImg1").value,
                    hotelImg2: document.getElementById("hotelImg2").value,
                    hotelImg3: document.getElementById("hotelImg3").value,
                    hotelImg4: document.getElementById("hotelImg4").value,
                    hotelImg5: document.getElementById("hotelImg5").value,
                    hotelImg6: document.getElementById("hotelImg6").value,
                    hotelText: document.getElementById("hotelText").value,
                    hotelFacility: document.getElementById("hotelFacility").value,
                    hotelCountry: document.getElementById("hotelCountry").value,
                    hotelTime: document.getElementById("hotelTime").value,
                    hotelCheck: document.getElementById("hotelCheck").value,
                    hotelAddr: document.getElementById("hotelAddr").value,
                    hotelDescription: document.getElementById("hotelDescription").value,
                    hotelSights: document.getElementById("hotelSights").value,
                    hotelTotal: document.getElementById("hotelTotal").value,
                    binID: document.getElementById("binID").value,
                    binCate: 2
                };

                let roomData = {
                    roomName: document.getElementById("roomName").value,
                    roomImg: document.getElementById("roomImg").value,
                    roomFacility: document.getElementById("roomFacility").value,
                    roomMax: document.getElementById("roomMax").value
                };

                 $.ajax({
                                type: "POST",
                                url: "/business/saveHotelAndRoom",
                                contentType: "application/json",
                                data: JSON.stringify({hotel: hotelData, room: roomData}),
                                success: function(response) {
                                    alert("Data saved successfully!");
                                    window.location.href = "/binpage/" + document.getElementById("binID").value;  // 성공 시 목록 페이지로 이동
                                },
                                error: function(error) {
                                    alert("Error saving data: " + error.responseText);
                                }
                            });
            } else {
                alert("빈칸을 채워주세요");
            }
        }

        function isFormValid() {
            let inputs = document.querySelectorAll("input[type='text'], input[type='number']");
            for (let input of inputs) {
                if (input.value.trim() === "") {
                    return false;
                }
            }
            return true;
        }

        function back() {
            window.location.href = "/binpage/" + document.getElementById("binID").value;
        }