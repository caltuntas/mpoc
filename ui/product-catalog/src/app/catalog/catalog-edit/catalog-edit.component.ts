import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'app-catalog-edit',
    templateUrl: './catalog-edit.component.html'
})
export class CatalogEditComponent implements OnInit {

    catalogId: string;

    constructor(private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.catalogId = this.route.snapshot.paramMap.get('catalogId');
    }

}
