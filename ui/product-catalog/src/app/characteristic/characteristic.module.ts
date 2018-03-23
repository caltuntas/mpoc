import { NgModule } from '@angular/core';
import { routing } from './characteristic.routing';
import { CommonModule } from '@angular/common';
import {FormsModule} from "@angular/forms";
import{ReactiveFormsModule} from "@angular/forms";
// Module components
import { CharacteristicCreateComponent } from './characteristic-create/characteristic-create.component';
import { CharacteristicListComponent } from "./characteristic-list/characteristic-list.component";

//Services
import { CharacteristicService } from './characteristic.service';

// Theme modules
import { SmartadminModule } from "../shared/smartadmin.module";
import { SmartadminDatatableModule } from "../shared/ui/datatable/smartadmin-datatable.module";
import { SmartadminValidationModule } from "../shared/forms/validation/smartadmin-validation.module";
import { SmartadminInputModule } from "../shared/forms/input/smartadmin-input.module";


@NgModule({
  imports: [
    routing,
    CommonModule,
    SmartadminModule,
    SmartadminDatatableModule,
    SmartadminValidationModule,
    SmartadminInputModule,
    FormsModule,
    ReactiveFormsModule

  ],
  declarations: [
    CharacteristicListComponent,
    CharacteristicCreateComponent
  ],
  providers: [
    CharacteristicService
  ]
})
export class CharacteristicModule { }
