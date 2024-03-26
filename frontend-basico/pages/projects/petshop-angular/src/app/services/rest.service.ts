import { Injectable } from '@angular/core';
import {
  AngularFireDatabase,
  AngularFireList,
} from '@angular/fire/compat/database';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class RestService {
  private basePath = '/tratamentos'; // Caminho para a coleção de dados no Firebase
  itemsRef: AngularFireList<any>; // Referência para a lista de itens

  constructor(private db: AngularFireDatabase) {
    this.itemsRef = db.list(this.basePath);
  }

  // Retorna todos os itens
  getItems(): Observable<any[]> {
    return this.itemsRef
      .snapshotChanges()
      .pipe(
        map((changes) =>
          changes.map((c) => ({ key: c.payload.key, ...c.payload.val() }))
        )
      );
  }

  // Retorna um item específico pelo seu ID
  getItem(key: string): Observable<any> {
    return this.db.object(`${this.basePath}/${key}`).valueChanges();
  }

  // Adiciona um novo item
  addItem(item: any): void {
    this.itemsRef.push(item);
  }

  // Atualiza um item existente
  updateItem(key: string, value: any): void {
    this.itemsRef.update(key, value);
  }

  // Retorna todos os itens
  searchItems(searchTerm: string): Observable<any[]> {
    return this.itemsRef
      .snapshotChanges()
      .pipe(
        map(changes =>
          changes.map(c => ({ key: c.payload.key, ...c.payload.val() }))
        ),
        map(items =>
          items.filter(item => this.matchKeyword(item, searchTerm))
        )
      );
  }

  // Verifica se o item contém a palavra-chave
  private matchKeyword(item: any, keyword: string): boolean {
    if (!keyword) {
      return true; // Retorna verdadeiro se não houver palavra-chave especificada
    }
    // Verifica se qualquer propriedade do item contém a palavra-chave
    for (const key in item) {
      if (Object.prototype.hasOwnProperty.call(item, key)) {
        const value = item[key];
        if (typeof value === 'string' && value.toLowerCase().includes(keyword.toLowerCase())) {
          return true; // Retorna verdadeiro se a palavra-chave for encontrada
        }
      }
    }
    return false; // Retorna falso se a palavra-chave não for encontrada em nenhum lugar no item
  }


  // Deleta um item
  deleteItem(key: string): void {
    this.itemsRef.remove(key);
  }

  // Deleta todos os itens
  deleteAll(): void {
    this.itemsRef.remove();
  }
}
