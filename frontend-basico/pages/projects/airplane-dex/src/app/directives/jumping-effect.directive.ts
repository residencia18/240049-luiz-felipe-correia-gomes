import { Directive, ElementRef, Renderer2, HostListener } from '@angular/core';

@Directive({
  selector: '[appJumpingEffect]',
  standalone: true
})
export class JumpingEffectDirective {
  constructor(private el: ElementRef, private renderer: Renderer2) {}

  @HostListener('mouseenter') onMouseEnter() {
    this.jump(1.1);
  }

  @HostListener('mouseleave') onMouseLeave() {
    this.jump(1.0);
  }

  private jump(scale: number) {
    this.renderer.setStyle(this.el.nativeElement, 'transform', `scale(${scale})`);
    setTimeout(() => {
      this.renderer.removeStyle(this.el.nativeElement, 'transform');
    }, 300);
  }
}
