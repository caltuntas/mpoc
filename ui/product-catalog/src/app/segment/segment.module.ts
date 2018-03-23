import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {segmentRouting} from './segment.routing';
import {SmartadminModule} from "../shared/smartadmin.module";
import {SegmentComponent} from "./segment.component";

@NgModule({
  imports: [
    CommonModule,
    segmentRouting,
    SmartadminModule
  ],
  declarations: [SegmentComponent]
})
export class SegmentModule { }
