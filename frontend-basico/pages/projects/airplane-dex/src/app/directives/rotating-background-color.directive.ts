// rotating-background-color.directive.ts
import { Directive, ElementRef, Renderer2, HostListener } from '@angular/core';

@Directive({
  selector: '[appRotatingBackgroundColor]',
  standalone: true
})
export class RotatingBackgroundColorDirective {
  private colors: string[] = ['#3498db', '#2ecc71', '#e74c3c', '#f39c12']; // Padrão de cores
  private currentIndex = 0;

  constructor(private el: ElementRef, private renderer: Renderer2) {}

  @HostListener('mouseenter') onMouseEnter() {
    this.rotateBackgroundColor();
  }

  private rotateBackgroundColor() {
    const nextColor = this.colors[this.currentIndex];
    this.renderer.setStyle(this.el.nativeElement, 'background-color', nextColor);

    // Atualiza o índice para a próxima cor
    this.currentIndex = (this.currentIndex + 1) % this.colors.length;
  }
}
