import {Component, OnInit} from '@angular/core';
import {CreateOfferingModel} from "../model/create-offering-model";
import {OfferingSpecModel} from "../model/offering-spec-model";
import {Catalog} from "../../catalog/model/catalog.model";
import {Router} from "@angular/router";
import {OfferingService} from "../offering.service";
import {CatalogService} from "../../catalog/catalog.service";
import {CharacteristicService} from "../../characteristic/characteristic.service";
import {specificationService} from "../../specification/specification.service";
import {Observable} from "rxjs/Observable";
import {ProdSpecCharValueUseListModel} from "../../characteristic/model/prod-spec-char-value-use-list.model";

@Component({
    selector: 'app-offering-edit',
    templateUrl: './offering-edit.component.html',
    styleUrls: ['./offering-edit.component.css'],
})
export class OfferingEditComponent implements OnInit {

    model: CreateOfferingModel;
    spesifications: Array<OfferingSpecModel> = [];
    catalogs: Array<Catalog> = [];
    charValueUseList: Array<ProdSpecCharValueUseListModel> = [];


    constructor(private router: Router,
                private offeringService: OfferingService,
                private catalogService: CatalogService,
                private charService: CharacteristicService,
                private specService: specificationService) {
        this.model = new CreateOfferingModel();
    }

    ngOnInit() {
        this.catalogService.getCatalogs().subscribe((catalogs) => {
            this.catalogs = catalogs;
        })

        this.specService.getSpecifications().subscribe((specs) => {
            this.spesifications = specs;
        })

        this.charService.getSpecCharValueUses(44).subscribe((charValuUseList) => {
            this.charValueUseList = charValuUseList;
            console.log(charValuUseList);
        })

    }

    onSpecSelected() {

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

