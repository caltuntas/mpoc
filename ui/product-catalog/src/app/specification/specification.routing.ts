import {NgModule} from '@angular/core';
import {ModuleWithProviders} from "@angular/core"
import {RouterModule, Routes} from "@angular/router";
import { SpecificationListComponent } from "./specification-list/specification-list.component";
import { SpecificationCreateComponent } from "./specification-create/specification-create.component";


export const routes:Routes = [

  {
    path: 'specification-list',
    component: SpecificationListComponent, data: {
      pageTitle: 'Specifications'
  }
  },

  {
    path: 'specification-create',
    component: SpecificationCreateComponent,
    data: {
      pageTitle: 'Create Specification'
  }
  }
];


export const routing = RouterModule.forChild(routes)
