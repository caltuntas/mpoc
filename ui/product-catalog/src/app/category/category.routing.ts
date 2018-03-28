import { NgModule } from '@angular/core';
import { ModuleWithProviders } from '@angular/core'
import { RouterModule, Routes } from '@angular/router';
import { CategoryListComponent } from './category-list.component';
import { CategoryCreateComponent } from './category-create.component';


export const routes: Routes = [
    {
        path: '',
        component: CategoryListComponent, data: {
            pageTitle: 'Categories'
        }
    },
    {
        path: 'new',
        component: CategoryCreateComponent,
        data: {
            pageTitle: 'Create Category'
        }
    },
    {
        path: ':id',
        component: CategoryCreateComponent, data: {
            pageTitle: 'Edit the category'
        }
    }
];


export const routing = RouterModule.forChild(routes)
