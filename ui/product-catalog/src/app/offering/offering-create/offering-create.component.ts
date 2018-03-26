import {Component, OnInit} from '@angular/core';
import {CreateOfferingModel} from "../model/create-offering-model";
import {OfferingService} from "../offering-service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-offering-create',
    templateUrl: './offering-create.component.html',
    styleUrls: ['./offering-create.component.css']
})
export class OfferingCreateComponent implements OnInit {

    model: CreateOfferingModel;

    constructor(private router: Router, private offeringService: OfferingService) {
        this.model = new CreateOfferingModel();
    }

    ngOnInit() {
    }

    public onSubmit() {
        console.log("form submitted");
        console.log(this.model);
        this.offeringService.createOffering(this.model).subscribe( data => {
            this.router.navigate(['/offering/offering-list']);
        } );
    }

}
