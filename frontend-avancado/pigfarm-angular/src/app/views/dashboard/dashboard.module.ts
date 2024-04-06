import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import {
  AvatarModule,
  ButtonGroupModule,
  ButtonModule,
  CardModule,
  CarouselModule,
  FormModule,
  GridModule,
  NavModule,
  ProgressModule,
  SpinnerModule,
  TableModule,
  TabsModule
} from '@coreui/angular';
import { IconModule } from '@coreui/icons-angular';
import { ChartjsModule } from '@coreui/angular-chartjs';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { WeightChartComponent } from './weight-chart/weight-chart.component';
import { ActivityChartComponent } from './activity-chart/activity-chart.component';
import { PigTableModule } from 'src/app/shared/pig-table/pig-table.module';

import { UtilsPipesModule } from 'src/app/shared/utils-pipes/utils-pipes.module';
import { FormatDatePipe } from 'src/app/services/pipes/format-date.pipe';
import { DashboardComponent } from './dashboard.component';

@NgModule({
  imports: [
    DashboardRoutingModule,
    CarouselModule,
    CardModule,
    NavModule,
    IconModule,
    TabsModule,
    CommonModule,
    GridModule,
    ProgressModule,
    ReactiveFormsModule,
    ButtonModule,
    FormModule,
    ButtonModule,
    ButtonGroupModule,
    ChartjsModule,
    AvatarModule,
    TableModule,
    SpinnerModule,
    UtilsPipesModule,
    PigTableModule,
  ],
  declarations: [
    DashboardComponent,
    WeightChartComponent,
    ActivityChartComponent,
  ],
  providers: [FormatDatePipe],
})
export class DashboardModule {
}
