import { NgModule } from '@angular/core';
import { UescAngularAppComponent } from './uesc-angular-app.component';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { MenuComponent } from './components/menu/menu.component';
import { NewsComponent } from './components/news/news.component';
import { HighlightsComponent } from './components/highlights/highlights.component';
import { ServicesComponent } from './components/services/services.component';
import { ResultsComponent } from './components/results/results.component';
import { FooterComponent } from './components/footer/footer.component';

@NgModule({
  declarations: [
    UescAngularAppComponent,
  ],
  imports: [CommonModule, HeaderComponent, MenuComponent, NewsComponent, HighlightsComponent, ServicesComponent, ResultsComponent, FooterComponent],
  providers: [],
  bootstrap: [UescAngularAppComponent]
})
export class UescAngularModule { }
