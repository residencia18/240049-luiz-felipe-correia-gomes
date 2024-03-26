import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AngularFireModule } from '@angular/fire/compat';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideHttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TreatmentRegistrationComponent } from './components/treatment-registration/treatment-registration.component';
import { TreatmentListComponent } from './components/treatment-list/treatment-list.component';
import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { TreatmentEditComponent } from './components/treatment-edit/treatment-edit.component';
import { TreatmentSearchComponent } from './components/treatment-search/treatment-search.component';
import { TreatmentResultComponent } from './components/treatment-result/treatment-result.component';
import { LoginComponent } from './components/login/login.component';
import { MaterialModule } from './material/material.module';
import { SignupComponent } from './components/signup/signup.component';

@NgModule({
  declarations: [
    AppComponent,
    TreatmentRegistrationComponent,
    TreatmentListComponent,
    ToolbarComponent,
    TreatmentEditComponent,
    TreatmentSearchComponent,
    TreatmentResultComponent,
    LoginComponent,
    SignupComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
  ],
  providers: [
    provideAnimationsAsync(),
    provideHttpClient(),
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
