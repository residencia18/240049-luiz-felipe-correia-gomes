import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HoverStyleDirective } from '../../directives/hover-style.directive';
import { JumpingEffectDirective } from '../../directives/jumping-effect.directive';

@Component({
  selector: 'app-category',
  standalone: true,
  imports: [CommonModule, HoverStyleDirective, JumpingEffectDirective],
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {
  @Input() categoryList: string[] = [];
  @Output() categorySelected = new EventEmitter<string>();

  filterByCategory(category: string) {
    this.categorySelected.emit(category);
  }
}
