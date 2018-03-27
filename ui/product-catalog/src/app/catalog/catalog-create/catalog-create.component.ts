import {Component, OnInit} from '@angular/core';
import {CreateCatalogModel} from "../model/create-catalog-model";
import {CatalogService} from "../catalog.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-catalog-create',
    templateUrl: './catalog-create.component.html'
})
export class CatalogCreateComponent implements OnInit {

    model: CreateCatalogModel;

    constructor(private router: Router, private catalogService: CatalogService) {
        this.model = new CreateCatalogModel();
    }

    ngOnInit() {
    }

    public onSubmit() {
        this.catalogService.createCatalog(this.model).subscribe(data => {
            this.router.navigate(['/catalog/catalog-list']);
        });
    }
}
