import { Component, OnInit } from '@angular/core';
import { NewsService } from '../../services/news.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-highlights',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './highlights.component.html',
  styleUrl: './highlights.component.css'
})
export class HighlightsComponent implements OnInit {

  newsList: HighlightItem[] = [];

  constructor(private newsService: NewsService) { }

  ngOnInit(): void {
    this.loadHightlights();
  }

  loadHightlights(): void {
    this.newsService.getNews('pt', 'top').subscribe({
      next: (data) => {
        // Processar os dados da API e criar os elementos HTML 
        for (let i = 0; i < 5 && i < data.results.length; i++) {
          const highlightItem = data.results[i];
         
          if(highlightItem.image_url == null) {
            continue;
          }

          this.newsList.push(highlightItem);
        }
      },
      error: (error) => {
        console.error('Erro ao buscar notícias: ', error);
      },
    });
  }
}

interface HighlightItem {
  title: string;
  image_url: string;
  link: string;
}
