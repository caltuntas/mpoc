import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'app-offering-edit',
    templateUrl: './offering-edit.component.html',
    styleUrls: ['./offering-edit.component.css']
})
export class OfferingEditComponent implements OnInit {

    offeringId: string;

    constructor(private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.offeringId = this.route.snapshot.paramMap.get('offeringId');
    }

}
