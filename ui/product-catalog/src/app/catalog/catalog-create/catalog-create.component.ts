import {Component, OnInit} from '@angular/core';
import {CreateCatalogModel} from "../model/create-catalog-model";
import {CatalogService} from "../catalog.service";

@Component({
    selector: 'app-catalog-create',
    templateUrl: './catalog-create.component.html'
})
export class CatalogCreateComponent implements OnInit {

    model: CreateCatalogModel;

    constructor(private catalogService: CatalogService) {
        this.model = new CreateCatalogModel();
    }

    ngOnInit() {
    }

    public onSubmit() {
        console.log("form submitted");
        console.log(this.model);
        this.catalogService.createCatalog(this.model).subscribe();
    }

}
