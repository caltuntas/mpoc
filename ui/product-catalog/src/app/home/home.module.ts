import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { homeRouting } from './home.routing';
import {SmartadminModule} from "../shared/smartadmin.module";
import {HomeComponent} from "./home.component";
//import {ChartsModule} from 'ng2-charts';


@NgModule({
  imports: [
    CommonModule, 
    homeRouting,
    SmartadminModule,
    //ChartsModule
  ],
  declarations: [HomeComponent]
})
export class HomeModule { }
