import {NgModule} from '@angular/core';
import {ModuleWithProviders} from '@angular/core'
import {RouterModule, Routes} from '@angular/router';
import { CatalogListComponent } from './catalog-list/catalog-list.component';
import { CatalogCreateComponent } from './catalog-create/catalog-create.component';


export const routes: Routes = [

    {
        path: 'catalog-list',
        component: CatalogListComponent, data: {
            pageTitle: 'Catalogs'
        }
    },

    {
        path: 'catalog-create',
        component: CatalogCreateComponent,
        data: {
            pageTitle: 'Create Catalog'
        }
    }
];


export const routing = RouterModule.forChild(routes)
