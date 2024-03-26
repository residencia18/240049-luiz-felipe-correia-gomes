import { NgModule } from '@angular/core';
import { AirplaneDexAppComponent } from './airplanedex-app.component';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { MenuCategoryComponent } from './components/menu-category/menu-category.component';

@NgModule({
  declarations: [
    AirplaneDexAppComponent,
  ],
  imports: [
    CommonModule,
    HeaderComponent,
    MenuCategoryComponent,
  ],
  providers: [],
  bootstrap: [AirplaneDexAppComponent]
})
export class AirplaneDexModule { }
