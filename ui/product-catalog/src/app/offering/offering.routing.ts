import {Routes, RouterModule} from '@angular/router';
import {OfferingComponent} from "./offering.component";
import {ModuleWithProviders} from "@angular/core";
import {OfferingListComponent} from "./offering-list/offering-list.component";

export const offeringRoutes: Routes = [

    {
        path: '',
        component: OfferingListComponent, data: {
            pageTitle: 'Offerings'
        }
    }

];

export const offeringRouting: ModuleWithProviders = RouterModule.forChild(offeringRoutes);

