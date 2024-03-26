import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class NewsService {
  private apiUrl = 'https://newsdata.io/api/1/news?';
  private pubKey = 'pub_34739c4beac627ca66f65beace647a95b315f';

  constructor(private http: HttpClient) {}

  getNews(language: string, category: string): Observable<{ results: NewsItem[] }> {
    const url = `${this.apiUrl}apikey=${this.pubKey}&language=${language}&category=${category}`;
    return this.http.get<{ results: NewsItem[] }>(url);
  }
}

interface NewsItem {
  title: string;
  image_url: string;
  link: string;
  pubDate: string;
}
