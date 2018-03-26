import {NgModule} from '@angular/core';
import {ModuleWithProviders} from "@angular/core"
import {RouterModule, Routes} from "@angular/router";
import { CharacteristicListComponent } from "./characteristic-list/characteristic-list.component";
import { CharacteristicCreateComponent } from "./characteristic-create/characteristic-create.component";


export const routes:Routes = [

  {
    path: 'characteristic-list',
    component: CharacteristicListComponent, data: {
      pageTitle: 'Characteristics'
  }
  },

  {
    path: 'characteristic-create',
    component: CharacteristicCreateComponent,
    data: {
      pageTitle: 'Create Characteristic'
  }
  }
];


export const routing: ModuleWithProviders = RouterModule.forChild(routes)
