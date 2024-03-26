import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopBarComponent } from './components/top-bar/top-bar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { WikipediaModule } from './projects/wikipedia-api/app/wikipedia.module';
import { UescAngularModule } from './projects/uesc-angular/app/uesc-angular-app.module';
import { AirplaneDexModule } from './projects/airplane-dex/app/airplanedex-app.module';
import { RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule} from '@angular/material/icon';

@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    WikipediaModule,
    UescAngularModule,
    AirplaneDexModule,
    MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
