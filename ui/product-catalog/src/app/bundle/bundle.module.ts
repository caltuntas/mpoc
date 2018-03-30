import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {bundleRouting} from './bundle.routing';
import {SmartadminModule} from "../shared/smartadmin.module";
import {OfferingComponent} from "./offering.component";
import {SmartadminInputModule} from "../shared/forms/input/smartadmin-input.module";
import {SmartadminValidationModule} from "../shared/forms/validation/smartadmin-validation.module";
import {SmartadminDatatableModule} from "../shared/ui/datatable/smartadmin-datatable.module";
import { BundleListComponent } from './bundle-list/bundle-list.component';
import { BundleEditComponent } from './bundle-edit/bundle-edit.component';
import {SmartadminFormsModule} from "../shared/forms/smartadmin-forms.module";
import { SalesChannelService } from '../sales-channel/sales-channel.service';
import { SegmentService } from '../segment/segment.service';
import { DocumentService } from '../document/document.service';

@NgModule({
    imports: [
        CommonModule,
        bundleRouting,
        SmartadminModule,
        SmartadminDatatableModule,
        SmartadminValidationModule,
        SmartadminInputModule,
        SmartadminFormsModule
    ],
    declarations: [BundleListComponent, BundleEditComponent],
    providers: [SalesChannelService,SegmentService,DocumentService]
})
export class BundleModule {
}
