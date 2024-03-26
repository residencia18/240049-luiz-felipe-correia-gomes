import { Directive, ElementRef, Input, Renderer2 } from '@angular/core';

@Directive({
  selector: '[appBorderStyle]',
  standalone: true
})
export class BorderStyleDirective {

  @Input() appBorderStyle: string = '1px solid #ccc';

  constructor(private el: ElementRef, private renderer: Renderer2) { }

  ngOnInit() {
    this.setStyle();
  }

  private setStyle() {
    this.renderer.setStyle(this.el.nativeElement, 'border', this.appBorderStyle);
  }


}
