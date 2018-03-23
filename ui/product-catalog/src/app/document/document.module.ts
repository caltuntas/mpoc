import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {documentRouting} from './document.routing';
import {SmartadminModule} from "../shared/smartadmin.module";
import {DocumentComponent} from "./document.component";

@NgModule({
  imports: [
    CommonModule,
    documentRouting,
    SmartadminModule
  ],
  declarations: [DocumentComponent]
})
export class DocumentModule { }
