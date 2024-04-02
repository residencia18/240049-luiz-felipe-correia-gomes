import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import { CalculateAgePipe } from '../../services/pipes/calculate-age.pipe';
import { FormatDatePipe } from '../../services/pipes/format-date.pipe';

@NgModule({
  declarations: [CalculateAgePipe, FormatDatePipe],
  imports: [
    CommonModule
  ],
  exports: [CalculateAgePipe, FormatDatePipe]
})
export class UtilsPipesModule { }
