import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-search',
  styleUrl: './search.component.css',
  template: `<input [(ngModel)]="searchTerm" placeholder="O que você procura?"/>
             <button (click)="search(searchTerm)">Pesquisar</button>`,
})
export class SearchComponent {
  searchTerm: string = '';
  @Output () searchEvent = new EventEmitter<string>();

  search(term: string) {
    this.searchEvent.emit(this.searchTerm);
  }

}
