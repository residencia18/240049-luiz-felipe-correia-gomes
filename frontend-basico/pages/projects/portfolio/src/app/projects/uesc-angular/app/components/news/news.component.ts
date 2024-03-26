import { Component, OnInit } from '@angular/core';
import { NewsService} from '../../services/news.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-news',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './news.component.html',
  styleUrl: './news.component.css'
})
export class NewsComponent implements OnInit {

  newsList: NewsItem[] = [];

  constructor(private newsService: NewsService) { }

  ngOnInit(): void {
    this.loadNews();
  }

  loadNews(): void {
    this.newsService.getNews('pt', 'science').subscribe({
      next: (data) => {
        // Processar os dados da API e criar os elementos HTML 
        for (let i = 0; i < 5 && i < data.results.length; i++) {
          const newsItem = data.results[i];
          this.newsList.push(newsItem);
        }
      },
      error: (error) => {
        console.error('Erro ao buscar notícias: ', error);
      },
    });
  }
}

interface NewsItem {
  title: string;
  link: string;
  pubDate: string;
}