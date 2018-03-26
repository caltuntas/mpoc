import { Component, OnInit } from '@angular/core';
import { CategoryCreateModel } from './categoryCreateModel';

@Component({
    selector: 'app-category-create',
    templateUrl: './category-create.component.html'
})
export class CategoryCreateComponent implements OnInit {

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

