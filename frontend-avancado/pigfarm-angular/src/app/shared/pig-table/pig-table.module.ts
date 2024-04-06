import { NgModule } from '@angular/core';
import { PigTableComponent } from './pig-table.component';
import { UtilsPipesModule } from '../utils-pipes/utils-pipes.module';
import {
  CardModule,
  GridModule,
  AvatarModule,
  TableModule,
  SpinnerModule,
} from '@coreui/angular';
import { IconModule } from '@coreui/icons-angular';

@NgModule({
  declarations: [PigTableComponent],
  imports: [
    CardModule,
    IconModule,
    GridModule,
    AvatarModule,
    TableModule,
    SpinnerModule,
    UtilsPipesModule,
  ],
  exports: [PigTableComponent],
})
export class PigTableModule {}
