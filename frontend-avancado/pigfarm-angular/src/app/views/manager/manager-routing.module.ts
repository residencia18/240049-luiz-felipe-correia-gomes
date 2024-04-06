import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListPigsComponent } from './list-pigs/list-pigs.component';
import { EditPigComponent } from './edit-pig/edit-pig.component';
import { WeightControlComponent } from './weight-control/weight-control.component';
import { queryParamGuard } from 'src/app/guards/query-param.guard';
import { SanitaryManagementComponent } from './sanitary-management/sanitary-management.component';
import { PigHistoryComponent } from './pig-history/pig-history.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Manager'
    },
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'list-pigs'
      },
      {
        path: 'list-pigs',
        component: ListPigsComponent,
        data: {
          title: 'List Pigs',
        },
      },
      {
        path: 'edit-pig',
        component: EditPigComponent,
        data: {
          title: 'Edit Pig',
        },
      },
      {
        path: 'weight-control',
        component: WeightControlComponent,
        data: {
          title: 'Weight Control',
        },
        canActivate: [queryParamGuard],
      },
      {
        path: 'sanitary-management',
        component: SanitaryManagementComponent,
        data: {
          title: 'Sanitary Management',
        },
      },
      {
        path: 'pig-history',
        component: PigHistoryComponent,
        data: {
          title: 'Pig History',
        },
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManagerRoutingModule {
}
