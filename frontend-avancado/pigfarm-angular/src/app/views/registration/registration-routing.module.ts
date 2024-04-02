import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterPigComponent } from './register-pig/register-pig.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Registration'
    },
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'register-pig'
      },
      {
        path: 'register-pig',
        component: RegisterPigComponent,
        data: {
          title: 'Register Pig'
        }
      },
    ]
  }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegistrationRoutingModule {
}
