"use strict";
document.addEventListener("DOMContentLoaded", function () {
    loadNews();
    loadHighlight();
    loadWeather();
    setInterval(loadWeather, 30 * 60 * 1000);
});
function loadNews() {
    const newsContainer = document.getElementById("news-list");
    const apiUrl = "https://newsdata.io/api/1/news?apikey=pub_34739c4beac627ca66f65beace647a95b315f&language=pt&category=science";
    fetch(apiUrl)
        .then((response) => response.json())
        .then((data) => {
        if (data.results && Array.isArray(data.results)) {
            for (let i = 0; i < 3 && i < data.results.length; i++) {
                const newsItem = data.results[i];
                const newsElement = document.createElement("div");
                newsElement.innerHTML = `
                            <a class="item-news" href="${newsItem.link}">
                                <h3 class="title-news">${newsItem.title}</h3>                        
                            </a>
                            <p class="pubdate">${newsItem.pubDate}</p>
                        `;
                newsContainer === null || newsContainer === void 0 ? void 0 : newsContainer.appendChild(newsElement);
            }
        }
    })
        .catch((error) => {
        console.log("Erro ao buscar notícias: ", error);
    });
}
function loadHighlight() {
    const highlightContainer = document.getElementById("highlights-list");
    const apiUrl = "https://newsdata.io/api/1/news?apikey=pub_34739c4beac627ca66f65beace647a95b315f&language=pt&category=top";
    fetch(apiUrl)
        .then((response) => response.json())
        .then((data) => {
        if (data.results && Array.isArray(data.results)) {
            for (let i = 0; i < 4 && i < data.results.length; i++) {
                const highlight = data.results[i];
                if (highlight.image_url == null) {
                    continue;
                }
                const highlightElement = document.createElement("div");
                highlightElement.innerHTML = `
                                <h3 class="title-highlight">${highlight.title}</h3>
                                <img class="image-highlight" src="${highlight.image_url}" />
                            `;
                highlightContainer === null || highlightContainer === void 0 ? void 0 : highlightContainer.appendChild(highlightElement);
            }
        }
    });
}
function loadWeather() {
    const apiKey = "7fc91c2469796840169634db0fb6a0fa";
    const lat = "-14.796708";
    const lon = "-39.173384";
    const apiUrl = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${apiKey}&units=metric&lang=pt_br`;
    const servicesContainer = document.getElementById("services");
    fetch(apiUrl)
        .then((response) => response.json())
        .then((data) => {
        if (data.main && data.weather && data.weather.length > 0) {
            const temperature = data.main.temp;
            const description = capitalize(data.weather[0].description);
            const weatherElement = document.createElement("div");
            weatherElement.innerHTML = `
                    <h3 class="title-weather">Condições Meteorológicas da UESC</h3>
                    <p>Temperatura: ${temperature}°C</p>
                    <p>${description}</p>`;
            servicesContainer === null || servicesContainer === void 0 ? void 0 : servicesContainer.appendChild(weatherElement);
        }
    })
        .catch((error) => {
        console.error("Erro ao buscar clima:", error);
    });
}
function capitalize(text) {
    return text
        .toLowerCase()
        .replace(/(?:^|\s)\S/g, (match) => match.toUpperCase());
}
