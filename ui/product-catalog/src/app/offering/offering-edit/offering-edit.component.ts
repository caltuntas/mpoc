import { Component, OnInit } from '@angular/core';
import { CreateOfferingModel } from "../model/create-offering-model";
import { OfferingSpecModel } from "../model/offering-spec-model";
import { Catalog } from "../../catalog/model/catalog.model";
import { Router } from "@angular/router";
import { OfferingService } from "../offering.service";
import { CatalogService } from "../../catalog/catalog.service";
import { CharacteristicService } from "../../characteristic/characteristic.service";
import { specificationService } from "../../specification/specification.service";
import { Observable } from "rxjs/Observable";
import { ProdSpecCharValueUseListModel } from "../../characteristic/model/prod-spec-char-value-use-list.model";
import { CategoryService } from '../../category/category.service';
import { Category } from '../../category/category.model';

@Component({
    selector: 'app-offering-edit',
    templateUrl: './offering-edit.component.html',
    styleUrls: ['./offering-edit.component.css'],
})
export class OfferingEditComponent implements OnInit {

    model: CreateOfferingModel;
    spesifications: Array<specificationListModel>=[];
    catalogs: Array<Catalog> = [];
    charValueUseList: Array<ProdSpecCharValueUseListModel> = [];
    categoryLeavesString: { [name: string]: string };
    categoryLeaves: Array<Category> = [];
    constructor(private router: Router,
        private offeringService: OfferingService,
        private catalogService: CatalogService,
        private charService: CharacteristicService,
        private specService: specificationService,
        private categoryService: CategoryService) {
        this.model = new CreateOfferingModel();
    }

    ngOnInit() {
        this.catalogService.getCatalogs().subscribe((catalogs) => {
            this.catalogs = catalogs;
        })

        this.specService.getSpecifications().subscribe((specs) => {
            this.spesifications = specs;
        })
        this.categoryService.getLeavesFullPathNames().subscribe((categoryLeavesString) => {
            this.categoryLeavesString = categoryLeavesString;
            //console.log(this.categoryLeavesString);
            //console.log(JSON.stringify(this.categoryLeavesString));
        })
    }

    onSpecSelected() {
        this.charService.getSpecCharValueUses(this.model.productSpecificationId).subscribe((charValuUseList) => {
            this.charValueUseList = charValuUseList;
        })
    }

    public onSubmit() {
        this.model.productSpecificationId = jQuery("#spesification").val();
        this.model.catalogId = jQuery("#catalogs").val();
        this.offeringService.createOffering(this.model).subscribe(data => {
            this.router.navigate(['/offering/offering-list']);
        });
        this.model.categoryId = jQuery("#categories").val();
    }

    onWizardComplete(data) {
        console.log('fuel-ux wizard complete', data)
    }

}

