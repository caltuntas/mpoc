import { Component, OnInit } from '@angular/core';
import { CatalogCreateModel } from './catalogCreateModel';

@Component({
    selector: 'app-catalog-create',
    templateUrl: './catalog-create.component.html'
})
export class CatalogCreateComponent implements OnInit {

    submitted = false;
    constructor() {

    }

    ngOnInit() {

    }

    onSubmit() {
        this.submitted = true;
        console.log('submitted')
    }

}
