import { Directive, ElementRef, Renderer2, Input, OnInit } from '@angular/core';

@Directive({
  selector: '[appHoverStyle]',
  standalone: true
})
export class HoverStyleDirective implements OnInit {
  @Input() backgroundColor: string = '#3498db';
  @Input() hoverColor: string = '#2980b9';

  constructor(private el: ElementRef, private renderer: Renderer2) {}

  ngOnInit() {
    this.setStyle();
  }

  private setStyle() {
    this.renderer.setStyle(this.el.nativeElement, 'cursor', 'pointer');
    this.renderer.setStyle(this.el.nativeElement, 'background-color', this.backgroundColor);

    this.renderer.listen(this.el.nativeElement, 'mouseover', () => {
      this.renderer.setStyle(this.el.nativeElement, 'background-color', this.hoverColor);
    });

    this.renderer.listen(this.el.nativeElement, 'mouseout', () => {
      this.renderer.setStyle(this.el.nativeElement, 'background-color', this.backgroundColor);
    });
  }
}
