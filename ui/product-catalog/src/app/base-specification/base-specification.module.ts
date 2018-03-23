import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {baseSpecificationRouting} from './base-specification.routing';
import {SmartadminModule} from "../shared/smartadmin.module";
import {BaseSpecificationComponent} from "./base-specification.component";

@NgModule({
  imports: [
    CommonModule,
    baseSpecificationRouting,
    SmartadminModule
  ],
  declarations: [BaseSpecificationComponent]
})
export class BaseSpecificationModule { }
