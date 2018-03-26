import {Component, OnInit} from '@angular/core';
import {CreateCategoryModel} from '../model/create-category-model';
import {Category} from '../model/category.model';
import { CategoryService } from '../category.service';
import { Router, NavigationExtras } from '@angular/router';
import { Observable } from 'rxjs/Observable';

@Component({
    selector: 'app-category-list',
    templateUrl: './category-list.component.html'
})
export class CategoryListComponent implements OnInit {

    public categories: Array<Category> = [];

    options = {
        dom: "Bfrtip",
        ajax: (data, callback, settings) => {
            this.categoryService.getAllCategories()
                .catch(this.handleError)
                .subscribe((data) => {
                    callback({
                        aaData: data
                    })
                })
        },
        columns: [
            {"data": "id"},
            {"data": "code"},
            {"data": "name"},
            {"data": "description"},
            {"data": "parentId"},
            {"data": "isRoot"},                        
            {
                render: (data, type, fullRow, meta) => {
                    return `
                        <div class='btn-group dropdown show'><button class='btn btn-info btn-sm dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>
                            <i class='fa fa-gear fa-lg'></i></button>
                            <ul class='dropdown-menu  ng-star-inserted'>                                
                                <li><a class='sa-datatables-edit' category-id='${fullRow.id}'>Edit</a></li>
                                <li><a class='sa-datatables-delete' category-id='${fullRow.id}'>Delete</a></li>
                            </ul>
                        </div>`;
                }
            }]
    };

    ngAfterViewInit() {
        document.querySelector('body').addEventListener('click', (event) => {
            let target = <Element>event.target;

            if (target.tagName.toLowerCase() === 'a' && jQuery(target).hasClass('sa-datatables-edit')) {
                this.onEdit(target.getAttribute('category-id'));
            }
            if (target.tagName.toLowerCase() === 'a' && jQuery(target).hasClass('sa-datatables-delete')) {
                this.onDelete(target.getAttribute('category-id'));
            }
        });
    }


    onEdit(categoryId) {
        console.log("edit category:", categoryId);

        let navigationExtras: NavigationExtras = {
            queryParams: {
                "categoryId": categoryId
            }
        };

        this.router.navigate(['/category/' + categoryId]);
    }

    onDelete(categoryId) {
        console.log("Delete category", categoryId, "?");
        this.categoryService.delete(categoryId).subscribe((data) => {
            window.location.reload();
        });
    }

    constructor(private router: Router, private categoryService: CategoryService) {
    }

    ngOnInit() {

    }

    private handleError(error: any) {
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }
}