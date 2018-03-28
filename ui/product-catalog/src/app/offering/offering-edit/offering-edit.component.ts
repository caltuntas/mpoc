import {Component, OnInit} from '@angular/core';
import {CreateOfferingModel} from "../model/create-offering-model";
import {OfferingSpecModel} from "../model/offering-spec-model";
import {Catalog} from "../../catalog/model/catalog.model";
import {Router} from "@angular/router";
import {OfferingService} from "../offering.service";
import {CatalogService} from "../../catalog/catalog.service";

@Component({
    selector: 'app-offering-edit',
    templateUrl: './offering-edit.component.html',
    styleUrls: ['./offering-edit.component.css'],
})
export class OfferingEditComponent implements OnInit {

    model: CreateOfferingModel;
    spesifications: Array<OfferingSpecModel> = [{"id": "3", "name": "adsl", "description": "adsl"},
        {"id": "4", "name": "fiber", "description": "fiber"}];

    catalogs: Array<Catalog> = [];

    constructor(private router: Router,
                private offeringService: OfferingService,
                private catalogService: CatalogService) {
        this.model = new CreateOfferingModel();
    }

    ngOnInit() {
        this.catalogService.getCatalogs().subscribe((catalogs) => {
            this.catalogs = catalogs;
        })
    }

    public onSubmit() {
        this.model.productSpecificationId = jQuery("#spesification").val();
        this.model.catalogId = jQuery("#catalogs").val();
        this.offeringService.createOffering(this.model).subscribe(data => {
            this.router.navigate(['/offering/offering-list']);
        });
    }

    onWizardComplete(data) {
        console.log('fuel-ux wizard complete', data)
    }

}

