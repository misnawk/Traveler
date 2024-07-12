$(document).ready(function() {
    let selectedRoom = null;
    const hotelNO = $('#go-to-payment').data('hotel-no'); // 호텔 번호 가져오기

    function fetchFacilities() {
        $.ajax({
            url: '/api/hotel/rooms/facilities/' + hotelNO,
            method: 'GET',
            success: function(response) {
                console.log('Facilities fetched:', response); // 데이터 확인용 로그
                displayFacilities(response);
            },
            error: function(error) {
                console.error('Error fetching facilities:', error);
            }
        });
    }

    function fetchRoomsByFacility(facility) {
        $.ajax({
            url: '/api/hotel/rooms/' + hotelNO + '/facility/' + facility,
            method: 'GET',
            success: function(response) {
                console.log('Rooms fetched for facility', facility, ':', response); // 데이터 확인용 로그
                displayRooms(response);
                openModal();
            },
            error: function(error) {
                console.error('Error fetching rooms:', error);
            }
        });
    }

    function displayFacilities(facilities) {
        const $facilitiesDiv = $('#facilities');
        $facilitiesDiv.empty();
        facilities.forEach(facility => {
            const facilityHtml = `<button class="facility" data-facility="${facility.roomFacility}">${facility.roomFacility} (${facility.count})</button>`;
            $facilitiesDiv.append(facilityHtml);
        });

        $('.facility').click(function() {
            const facility = $(this).data('facility');
            fetchRoomsByFacility(facility);
        });
    }

    function displayRooms(rooms) {
        const $roomsDiv = $('#rooms');
        $roomsDiv.empty();
        rooms.forEach(room => {
            const roomHtml = `
                <div class="room" data-hotel-no="${room.hotelNO}" data-room-name="${room.roomName}" data-room-facility="${room.roomFacility}" data-room-max="${room.roomMax}">
                    <img src="${room.roomImg}" alt="${room.roomName}">
                    <div>호텔 번호: ${room.hotelNO}</div>
                    <div>룸 타입: ${room.roomName}</div>
                    <div>시설: ${room.roomFacility}</div>
                    <div>최대 인원: ${room.roomMax}</div>
                    <button class="select-room">선택</button>
                </div>
            `;
            $roomsDiv.append(roomHtml);
        });

        $('.select-room').click(function() {
            const room = $(this).closest('.room');
            selectedRoom = {
                hotelNO: room.data('hotel-no'),
                roomName: room.data('room-name'),
                roomFacility: room.data('room-facility'),
                roomMax: room.data('room-max')
            };
            closeModal();
            updateSelectedRoomInfo();
        });
    }

    function updateSelectedRoomInfo() {
        if (selectedRoom) {
            $('.selection h3').text(`선택된 객실: ${selectedRoom.roomName}`);
            $('.selection p').text(`시설: ${selectedRoom.roomFacility}, 최대 인원: ${selectedRoom.roomMax}`);
        }
    }

    function openModal() {
        $('#roomsModal').css('display', 'block');
    }

    function closeModal() {
        $('#roomsModal').css('display', 'none');
    }

    $('.close').click(function() {
        closeModal();
    });

    $(window).click(function(event) {
        if (event.target === $('#roomsModal')[0]) {
            closeModal();
        }
    });

    $('#go-to-payment').click(function() {
        const checkin = $(this).data('checkin') || '';
        const checkout = $(this).data('checkout') || '';
        const guestCount = $(this).data('guest-count') || 1;

           // 로그인 여부 확인
                   $.ajax({
                       url: '/check-login',
                       type: 'GET',
                       dataType: 'json',
                       xhrFields: {
                           withCredentials: true
                       },
                       success: function(response) {
                           if (!response || !response.loggedIn) {
                               alert("로그인이 필요한 서비스입니다. 로그인 페이지로 이동합니다.");
                               window.location.href = '/login';
                           } else {
                               // 로그인 되어 있을 때, 다른 조건 체크
                               if (!checkin || !checkout || !guestCount) {
                                   alert('여행지, 날짜, 인원수를 작성해야 합니다. 검색창으로 이동합니다.');
                                   window.location.href = '/hotel';
                               } else if (!selectedRoom) {
                                   alert('숙소를 선택해주세요.');
                               } else {
                                   window.location.href = `/hotelPayment/${hotelNO}?checkin=${checkin}&checkout=${checkout}&guestCount=${guestCount}&roomName=${selectedRoom.roomName}&roomFacility=${selectedRoom.roomFacility}&roomMax=${selectedRoom.roomMax}`;
                               }
                           }
                       },
                       error: function(jqXHR, textStatus, errorThrown) {
                           alert('서버와의 통신 중 오류가 발생했습니다. 상세 내용: ' + textStatus);
                       }
                   });
               });


    fetchFacilities();
});
