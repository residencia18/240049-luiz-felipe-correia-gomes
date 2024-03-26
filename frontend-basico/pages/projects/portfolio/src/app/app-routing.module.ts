import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UescAngularAppComponent } from './projects/uesc-angular/app/uesc-angular-app.component';
import { WikipediaAppComponent } from './projects/wikipedia-api/app/wikipedia-app.component';
import { AirplaneDexAppComponent } from './projects/airplane-dex/app/airplanedex-app.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent }, // Rota padrão apontando para o componente inicial
  {path: 'uesc-angular', component: UescAngularAppComponent },
  {path: 'wikipedia-api', component: WikipediaAppComponent },
  {path: 'airplane-dex', component: AirplaneDexAppComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
