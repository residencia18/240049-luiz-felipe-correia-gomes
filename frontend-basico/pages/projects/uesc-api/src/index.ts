document.addEventListener("DOMContentLoaded", function () {
  loadNews();
  loadHighlight();
  loadWeather();

  // Atualizar o clima a cada 30 minutos (em milissegundos)
  setInterval(loadWeather, 30 * 60 * 1000);
});

function loadNews() {
  const newsContainer = document.getElementById("news-list");
  const apiUrl =
    "https://newsdata.io/api/1/news?apikey=pub_34739c4beac627ca66f65beace647a95b315f&language=pt&category=science";

  // Interface para representar a estrutura da resposta
  interface NewsItem {
    title: string;
    link: string;
    pubDate: string;
  }

  // Fazer solicitação à API
  fetch(apiUrl)
    .then((response) => response.json() as Promise<{ results: NewsItem[] }>)
    .then((data) => {
      // Verifique se 'data' contém a chave 'results' antes de usar forEach
      if (data.results && Array.isArray(data.results)) {
        // Processar os dados da API e criar os elementos HTML
        for (let i = 0; i < 3 && i < data.results.length; i++) {
          const newsItem = data.results[i];
          const newsElement = document.createElement("div");
          newsElement.innerHTML = `
                            <a class="item-news" href="${newsItem.link}">
                                <h3 class="title-news">${newsItem.title}</h3>                        
                            </a>
                            <p class="pubdate">${newsItem.pubDate}</p>
                        `;
          newsContainer?.appendChild(newsElement);
        }
      }
    })
    .catch((error) => {
      console.log("Erro ao buscar notícias: ", error);
    });
}

function loadHighlight() {
  const highlightContainer = document.getElementById("highlights-list");
  const apiUrl =
    "https://newsdata.io/api/1/news?apikey=pub_34739c4beac627ca66f65beace647a95b315f&language=pt&category=top";

  interface highlight {
    title: string;
    image_url: string;
  }

  fetch(apiUrl)
    .then((response) => response.json() as Promise<{ results: highlight[] }>)
    .then((data) => {
      if (data.results && Array.isArray(data.results)) {
        for (let i = 0; i < 4 && i < data.results.length; i++) {
          const highlight = data.results[i];
          if(highlight.image_url == null) {
                continue;
            }
          const highlightElement = document.createElement("div");
          highlightElement.innerHTML = `
                                <h3 class="title-highlight">${highlight.title}</h3>
                                <img class="image-highlight" src="${highlight.image_url}" />
                            `;
          highlightContainer?.appendChild(highlightElement);
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
      // Verificar se a resposta possui dados válidos
      if (data.main && data.weather && data.weather.length > 0) {
        const temperature = data.main.temp;
        const description = capitalize(data.weather[0].description);

        const weatherElement = document.createElement("div");
        weatherElement.innerHTML = `
                    <h3 class="title-weather">Condições Meteorológicas da UESC</h3>
                    <p>Temperatura: ${temperature}°C</p>
                    <p>${description}</p>`;

        servicesContainer?.appendChild(weatherElement);
      }
    })
    .catch((error) => {
      console.error("Erro ao buscar clima:", error);
    });
}

function capitalize(text: string) {
  return text
    .toLowerCase()
    .replace(/(?:^|\s)\S/g, (match) => match.toUpperCase());
}
