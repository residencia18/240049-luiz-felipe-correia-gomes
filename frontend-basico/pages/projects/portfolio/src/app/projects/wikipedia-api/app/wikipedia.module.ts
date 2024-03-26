import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { WikipediaAppComponent } from './wikipedia-app.component';
import { SearchComponent } from './components/search/search.component';
import { FormsModule } from '@angular/forms';
import { ArticleComponent } from './components/article/article.component';
import { HttpClient, HttpClientJsonpModule, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    WikipediaAppComponent,
    SearchComponent,
    ArticleComponent,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    HttpClientJsonpModule,
    FormsModule
  ],
  providers: [HttpClient, HttpClientJsonpModule, CommonModule],
  bootstrap: [WikipediaAppComponent]
})
export class WikipediaModule { }
