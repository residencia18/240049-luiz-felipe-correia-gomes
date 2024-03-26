import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TreatmentRegistrationComponent } from './components/treatment-registration/treatment-registration.component';
import { TreatmentListComponent } from './components/treatment-list/treatment-list.component';
import { TreatmentEditComponent } from './components/treatment-edit/treatment-edit.component';
import { TreatmentResultComponent } from './components/treatment-result/treatment-result.component';
import { authGuard } from './guards/auth.guard';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';

const routes: Routes = [
  { path: '', redirectTo: 'cadastro', pathMatch: 'full' },
  { path: 'cadastro', component: TreatmentRegistrationComponent, canActivate: [authGuard] },
  { path: 'listagem', component: TreatmentListComponent, canActivate: [authGuard] },
  { path: 'edicao', component: TreatmentEditComponent, canActivate: [authGuard] },
  { path: 'resultados/:searchTerm', component: TreatmentResultComponent, canActivate: [authGuard] },
  { path: 'signup', component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: 'cadastro', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
