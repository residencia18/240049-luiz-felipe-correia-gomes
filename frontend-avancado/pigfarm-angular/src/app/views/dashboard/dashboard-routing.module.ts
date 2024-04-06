import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './dashboard.component';
import { WeightChartComponent } from './weight-chart/weight-chart.component';
import { queryParamGuard } from 'src/app/guards/query-param.guard';

const routes: Routes = [
  {
    path: '',
    component: DashboardComponent,
    data: {
      title: 'Dashboard',
    },
    children: [
      {
        path: 'weight-chart',
        component: WeightChartComponent,
        data: {
          title: 'Weight Chart',
        },
      },
    ],
    canActivate: [queryParamGuard],
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule {
}
