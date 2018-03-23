import {Routes, RouterModule } from '@angular/router';
import {SegmentComponent} from "./segment.component";
import {ModuleWithProviders} from "@angular/core";

export const segmentRoutes: Routes = [
    {
        path: '',
        component: SegmentComponent,
        data: {
            pageTitle: 'Segment'
        }
    }
];

export const segmentRouting: ModuleWithProviders = RouterModule.forChild(segmentRoutes);

 