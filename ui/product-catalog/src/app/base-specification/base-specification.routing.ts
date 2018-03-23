import {Routes, RouterModule } from '@angular/router';
import {BaseSpecificationComponent} from "./base-specification.component";
import {ModuleWithProviders} from "@angular/core";

export const baseSpecificationRoutes: Routes = [
    {
        path: '',
        component: BaseSpecificationComponent,
        data: {
            pageTitle: 'BaseSpecificaiton'
        }
    }
];

export const baseSpecificationRouting: ModuleWithProviders = RouterModule.forChild(baseSpecificationRoutes);

 