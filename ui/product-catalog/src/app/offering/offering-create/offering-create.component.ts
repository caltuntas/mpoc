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
    spesifications: Array<OfferingSpecModel> = [{"id": "1", "name": "adsl", "description": "adsl"},
                                                {"id": "2", "name": "fiber", "description": "fiber"}];

    constructor(private router: Router, private offeringService: OfferingService) {
        this.model = new CreateOfferingModel();
    }

    ngOnInit() {
    }

    public onSubmit() {
        this.model.productSpecificationId = jQuery("#spesification").val();
        console.log(this.model)
        this.offeringService.createOffering(this.model).subscribe(data => {
            this.router.navigate(['/offering/offering-list']);
        });
    }

}
