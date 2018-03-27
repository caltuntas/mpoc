import {Component, OnInit} from '@angular/core';
import {OfferingService} from "../offering.service";
import {Router} from "@angular/router";
import {CreateOfferingModel} from "../model/create-offering-model";
import {OfferingSpecModel} from "../model/offering-spec-model";

@Component({
    selector: 'app-offering-create',
    templateUrl: './offering-create.component.html',
    styleUrls: ['./offering-create.component.css']
})
export class OfferingCreateComponent implements OnInit {

    model: CreateOfferingModel;
    spesifications: Array<OfferingSpecModel> = [{"id": 1, "name": "name", "description": "dede"}];

    constructor(private router: Router, private offeringService: OfferingService) {
        this.model = new CreateOfferingModel();
    }

    ngOnInit() {
    }

    public onSubmit() {
        this.offeringService.createOffering(this.model).subscribe(data => {
            this.router.navigate(['/offering/offering-list']);
        });
    }

}
