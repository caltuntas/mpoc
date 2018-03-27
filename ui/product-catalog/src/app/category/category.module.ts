import {NgModule} from '@angular/core';
import {routing} from './category.routing';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
// Module components
import {CategoryCreateComponent} from './category-create/category-create.component';
import {CategoryListComponent} from './category-list/category-list.component';

// Services
import {CategoryService} from './category.service';


// Theme modules
import {SmartadminModule} from '../shared/smartadmin.module';
import {SmartadminDatatableModule} from '../shared/ui/datatable/smartadmin-datatable.module';
import {SmartadminValidationModule} from '../shared/forms/validation/smartadmin-validation.module';
import {SmartadminInputModule} from '../shared/forms/input/smartadmin-input.module';

@NgModule({
    imports: [
        routing,
        CommonModule,
        SmartadminModule,
        SmartadminDatatableModule,
        SmartadminValidationModule,
        SmartadminInputModule,
        FormsModule

    ],
    declarations: [
        CategoryListComponent,
        CategoryCreateComponent,
        //CategoryEditComponent
    ],
    providers: [
        CategoryService
    ]
})
export class CategoryModule {
}
