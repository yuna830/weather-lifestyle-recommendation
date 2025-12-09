const weatherCards = document.querySelectorAll('.weather-card');
const currentWeatherText = document.getElementById('currentWeather');
const typeChipGroup = document.getElementById('typeChipGroup');
const limitMessage = document.getElementById('limitMessage');
const recommendationArea = document.getElementById('recommendationArea');
const emptyMessage = document.getElementById('emptyMessage');

let selectedWeather = null;
const selectedTypes = new Set();
const recommendationsByType = {};

const weatherLabelMap = {
    sunny: '맑음',
    rainy: '비',
    snowy: '눈',
    cold: '추움 / 강풍',
    cloudy: '구름 많음',
    foggy: '안개 많음',
    thunder: '천둥·번개'
};

const typeLabelMap = {
    music: '음악',
    food: '음식',
    outfit: '옷',
    activity: '활동',
    place: '장소',
    perfume: '향수'
};

weatherCards.forEach((card) => {
    card.addEventListener('click', () => {
        const weather = card.getAttribute('data-weather');
        selectedWeather = weather;

        weatherCards.forEach((c) => c.classList.remove('selected'));
        card.classList.add('selected');

        currentWeatherText.textContent = weatherLabelMap[weather] || weather;

        selectedTypes.clear();
        Object.keys(recommendationsByType).forEach((key) => {
            delete recommendationsByType[key];
        });

        updateTypeChipsUI();
        renderRecommendations();
    });
});

typeChipGroup.addEventListener('click', async(event) => {
    const target = event.target.closest('.type-chip');
    if (!target) return;

    const type = target.getAttribute('data-type');

    if (!selectedWeather) {
        alert('먼저 왼쪽에서 오늘 날씨를 선택해 주세요.');
        return;
    }

    if (selectedTypes.has(type)) {
        selectedTypes.delete(type);
        delete recommendationsByType[type];
        updateTypeChipsUI();
        renderRecommendations();
        return;
    }

    // 최대 2개 제한
    if (selectedTypes.size >= 2) {
        limitMessage.textContent = '항목은 동시에 최대 2개까지 선택할 수 있습니다.';
        return;
    } else {
        limitMessage.textContent = '';
    }

    selectedTypes.add(type);
    updateTypeChipsUI();

    try {
        const url =
            `/api/weather-recommend?weather=${encodeURIComponent(selectedWeather)}&type=${encodeURIComponent(type)}`;
        const res = await fetch(url);

        if (!res.ok) {
            alert('추천을 불러오지 못했습니다.');
            selectedTypes.delete(type);
            updateTypeChipsUI();
            return;
        }

        const items = await res.json();
        recommendationsByType[type] = items;
        renderRecommendations();
    } catch (error) {
        console.error(error);
        alert('서버 통신 중 오류가 발생했습니다.');
        selectedTypes.delete(type);
        updateTypeChipsUI();
    }
});

function updateTypeChipsUI() {
    const chips = document.querySelectorAll('.type-chip');

    chips.forEach((chip) => {
        const type = chip.getAttribute('data-type');

        chip.classList.remove('selected', 'disabled');

        if (selectedTypes.has(type)) {
            chip.classList.add('selected');
        } else if (selectedTypes.size >= 2) {
            chip.classList.add('disabled');
        }
    });
}

function renderRecommendations() {
    recommendationArea.innerHTML = '';

    if (!selectedWeather) {
        emptyMessage.textContent = '왼쪽에서 날씨를 먼저 선택해 주세요.';
        recommendationArea.appendChild(emptyMessage);
        return;
    }

    if (selectedTypes.size === 0) {
        emptyMessage.textContent =
            '추천받을 항목(음악, 음식, 옷 등)을 위에서 선택해 주세요. (최대 2개)';
        recommendationArea.appendChild(emptyMessage);
        return;
    }

    emptyMessage.textContent = '';

    selectedTypes.forEach((type) => {
        const block = document.createElement('div');
        block.className = 'category-block';

        const title = document.createElement('div');
        title.className = 'category-title';
        title.textContent = `${typeLabelMap[type] || type} 추천`;

        const grid = document.createElement('div');
        grid.className = 'items-grid';

        const items = recommendationsByType[type] || [];

        if (items.length === 0) {
            const msg = document.createElement('div');
            msg.className = 'empty-message';
            msg.textContent = '추천 결과가 없습니다.';
            block.appendChild(title);
            block.appendChild(msg);
        } else {
            items.forEach((item) => {
                const card = document.createElement('div');
                card.className = 'item-card';

                const img = document.createElement('img');
                img.className = 'item-image';
                img.src = item.imageUrl || '';
                img.alt = item.main || '';

                const texts = document.createElement('div');

                const main = document.createElement('div');
                main.className = 'item-main';
                main.textContent = item.main;

                const sub = document.createElement('div');
                sub.className = 'item-sub';
                sub.textContent = item.sub;

                texts.appendChild(main);
                texts.appendChild(sub);

                card.appendChild(img);
                card.appendChild(texts);
                grid.appendChild(card);
            });

            block.appendChild(title);
            block.appendChild(grid);
        }

        recommendationArea.appendChild(block);
    });
}