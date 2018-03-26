import {Component, OnInit} from '@angular/core';
import {CreateCategoryModel} from '../model/create-category-model';
import {Category} from '../model/category.model';
import { CategoryService } from '../category.service';

@Component({
    selector: 'app-category-list',
    templateUrl: './category-list.component.html'
})
export class CategoryListComponent implements OnInit {

    public options;
    public categories: Array<Category> = [];

    constructor(private categoryService: CategoryService) {
    }

    ngOnInit() {
        // this.createCategory();
        this.getAllCategories();
    }

    createCategory() {
        const category =
            new CreateCategoryModel(
                'test',
                '2012-04-23T18:25:43.511Z',
                '2012-04-23T18:25:43.511Z',
                'desc',
                '1',
                1,
                false
            );

        this.categoryService.createCategory(category).subscribe();
    }

    
    getAllCategories() {
        this.categoryService.getAllCategories().subscribe((categories) => {
            this.categories = categories;
            this.options = {
                'data': this.categories,
                'iDisplayLength': 15,
                'columns': [
                    {'data': 'id'},
                    {'data': 'name'},
                    {'data': 'description'},
                    {'data': 'validForEndDate'},
                    {'data': 'validForStartDate'},
                    {
                        'orderable': false,
                        'defaultContent': '   <a (click)=\'(null)\' class=\'btn btn-primary\'><i class=\'fa fa-edit\'></i> Edit</a>'
                    }

                ],
                'order': [[1, 'asc']]
            }
        });
    }
}
