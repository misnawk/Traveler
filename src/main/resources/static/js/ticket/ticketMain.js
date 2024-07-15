const images = [
    { title: '일본', filenames: ['일본.webp'] },
    { title: '호주', filenames: ['호주.webp'] },
    { title: '인도', filenames: ['인도.webp'] },
    { title: '인도네시아', filenames: ['인도네시아.webp'] },
    { title: '필리핀', filenames: ['필리핀.webp'] },
    { title: '대만', filenames: ['대만.webp'] },
    { title: '태국', filenames: ['태국.webp'] },
    { title: '베트남', filenames: ['베트남.webp'] }
];

let tickets = [];

document.addEventListener('DOMContentLoaded', async function() {
    try {
        const response = await fetch('/api/tickets');
        tickets = await response.json();
        renderTickets(tickets);
    } catch (error) {
        console.error('Error fetching tickets:', error);
    }

    const categoryButtons = document.querySelectorAll('.category');
    categoryButtons.forEach(button => {
        button.addEventListener('click', function() {
            const category = this.getAttribute('data-category');
            const filteredTickets = tickets.filter(ticket => ticket.category === category);
            renderTickets(filteredTickets);
        });
    });
});

function renderTickets(tickets) {
    const ticketSections = document.querySelector('.ticket_sections');
    if (!ticketSections) return;

    ticketSections.innerHTML = images.map(country => {
        const countryTickets = tickets.filter(ticket => ticket.tickPlace === country.title);
        if (countryTickets.length === 0) return '';

        return `
            <div class="country_tickets">
                <div class="country_mark">
                    <img src="/imgs/country/${country.filenames[0]}" alt="${country.title}">
                    <h2>${country.title}</h2>
                </div>
                <div class="ticket_list_container">
                    <button class="arrow left_arrow" onclick="prevSlide(this)">&#9664;</button>
                    <div class="ticket_list">
                        ${countryTickets.map(ticket => `
                        <div class="ticket_card" onclick="viewDetails('${ticket.tickNO}')">
                                <img src="${ticket.tickImg}" alt="${ticket.tickTitle}">
                                <h4>${ticket.tickTitle}</h4>
                                <p>${ticket.tickText}</p>
                                <p class="price">가격: ${ticket.tickPrice.toLocaleString()} 원</p>
                                <p>날짜: ${new Date(ticket.tickDate).toLocaleDateString()}</p>
                                <p>${ticket.tickOp}</p>
                            </div>
                        `).join('')}
                    </div>
                    <button class="arrow right_arrow" onclick="nextSlide(this)">&#9654;</button>
                </div>
            </div>
        `;
    }).join('');
}

function nextSlide(button) {
    const ticketList = button.previousElementSibling;
    ticketList.scrollBy({ left: 300, behavior: 'smooth' });
}

function prevSlide(button) {
    const ticketList = button.nextElementSibling;
    ticketList.scrollBy({ left: -300, behavior: 'smooth' });
}

function viewDetails(tickNO) {
    window.location.href = `/ticket/details/${tickNO}`;
}
