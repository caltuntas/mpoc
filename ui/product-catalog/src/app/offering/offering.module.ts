import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {offeringRouting} from './offering.routing';
import {SmartadminModule} from "../shared/smartadmin.module";
import {OfferingComponent} from "./offering.component";
import {SmartadminInputModule} from "../shared/forms/input/smartadmin-input.module";
import {SmartadminValidationModule} from "../shared/forms/validation/smartadmin-validation.module";
import {SmartadminDatatableModule} from "../shared/ui/datatable/smartadmin-datatable.module";
import { OfferingListComponent } from './offering-list/offering-list.component';
import { OfferingCreateComponent } from './offering-create/offering-create.component';
import { OfferingEditComponent } from './offering-edit/offering-edit.component';

@NgModule({
    imports: [
        CommonModule,
        offeringRouting,
        SmartadminModule,
        SmartadminDatatableModule,
        SmartadminValidationModule,
        SmartadminInputModule
    ],
    declarations: [OfferingListComponent, OfferingCreateComponent, OfferingEditComponent]
})
export class OfferingModule {
}
