import {Routes, RouterModule } from '@angular/router';
import {DocumentComponent} from "./document.component";
import {ModuleWithProviders} from "@angular/core";

export const documentRoutes: Routes = [
    {
        path: '',
        component: DocumentComponent,
        data: {
            pageTitle: 'Document'
        }
    }
];

export const documentRouting: ModuleWithProviders = RouterModule.forChild(documentRoutes);

 