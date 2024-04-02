import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { RegisterPigComponent } from './register-pig/register-pig.component';

import { RegistrationRoutingModule } from './registration-routing.module';


import {
  ButtonGroupModule,
  ButtonModule,
  CardModule,
  CollapseModule,
  DropdownModule,
  FormModule,
  GridModule,
  ModalModule,
  NavbarModule,
  NavModule,
  PaginationModule,
  SharedModule,
  UtilitiesModule,
  AvatarModule,
  ProgressModule,
  TableModule,
  TabsModule,
} from '@coreui/angular';

import { IconModule } from '@coreui/icons-angular';
import { UtilsPipesModule } from 'src/app/shared/utils-pipes/utils-pipes.module';

@NgModule({
  declarations: [
    RegisterPigComponent,
  ],
  imports: [
    CommonModule,
    RegistrationRoutingModule,
    ButtonModule,
    ButtonGroupModule,
    GridModule,
    IconModule,
    CardModule,
    UtilitiesModule,
    DropdownModule,
    SharedModule,
    FormModule,
    NavbarModule,
    CollapseModule,
    NavModule,
    NavbarModule,
    FormsModule,
    ReactiveFormsModule,
    ModalModule,
    PaginationModule,
    AvatarModule,
    ProgressModule,
    TableModule,
    TabsModule,
    UtilsPipesModule,
  ],
})
export class RegistrationModule {
}
