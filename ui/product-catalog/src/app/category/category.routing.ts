import { NgModule } from '@angular/core';
import { ModuleWithProviders } from '@angular/core'
import { RouterModule, Routes } from '@angular/router';
import { CategoryListComponent } from './category-list/category-list.component';
import { CategoryCreateComponent } from './category-create/category-create.component';


export const routes: Routes = [

    {
        path: 'category-list',
        component: CategoryListComponent, data: {
            pageTitle: 'Categories'
        }
    },

    {
        path: 'category-create',
        component: CategoryCreateComponent,
        data: {
            pageTitle: 'Create Category'
        }
    },

    {
        path: 'category-edit/:categoryId',
        /*component: CategoryEditComponent, */data: {
            pageTitle: 'Edit the category'
        }
    }
];


export const routing = RouterModule.forChild(routes)
