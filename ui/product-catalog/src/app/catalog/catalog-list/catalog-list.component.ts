import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {CatalogService} from "../catalog.service";
import {NavigationExtras, Router, Routes} from "@angular/router";
import {Catalog} from "../model/catalog.model";

@Component({
    selector: 'app-catalog-list',
    templateUrl: './catalog-list.component.html'
})
export class CatalogListComponent implements OnInit {

    catalogs: Array<Catalog> = [];
    selectedCatalog: Catalog;

    options = {
        dom: "Bfrtip",
        ajax: (data, callback, settings) => {
            this.catalogService.getCatalogs()
                .map((data: any) => (data.data || data))
                .catch(this.handleError)
                .subscribe((data) => {
                    this.catalogs = data;
                    callback({
                        aaData: data.slice(0, 100)
                    })
                })
        },
        columns: [
            {"data": "id"},
            {"data": "name"},
            {"data": "description"},
            {"data": "validForStartDate"},
            {"data": "validForEndDate"},
            {
                render: (data, type, fullRow, meta) => {
                    return `
                        <div class='btn-group dropdown show'><button class='btn btn-info btn-sm dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>
                            <i class='fa fa-gear fa-lg'></i></button>
                            <ul class='dropdown-menu  ng-star-inserted'>                                
                                <li><a class='sa-datatables-edit' catalog-id='${fullRow.id}'>Edit</a></li>
                                <li><a class='sa-datatables-delete' catalog-id='${fullRow.id}'>Delete</a></li>
                            </ul>
                        </div>`;
                }
            }]
    };

    ngAfterViewInit() {
        document.querySelector('body').addEventListener('click', (event) => {
            let target = <Element>event.target;

            if (target.tagName.toLowerCase() === 'a' && jQuery(target).hasClass('sa-datatables-edit')) {
                this.onEditCatalog(target.getAttribute('catalog-id'));
            }
            if (target.tagName.toLowerCase() === 'a' && jQuery(target).hasClass('sa-datatables-delete')) {
                this.onDeleteCatalog(target.getAttribute('catalog-id'));
            }
        });
    }


    onEditCatalog(catalogId) {
        console.log("edit catalog:", catalogId);

        let navigationExtras: NavigationExtras = {
            queryParams: {
                "catalogId": catalogId
            }
        };

        this.router.navigate(['/catalog/catalog-edit/' + catalogId]);
    }

    onDeleteCatalog(catalogId) {
        console.log("The catalog with id: ", catalogId, " will be deleted. Do you confirm?");
        this.catalogService.deleteCatalog(catalogId).subscribe((data) => {
            window.location.reload();
        });
    }

    constructor(private router: Router, private catalogService: CatalogService) {
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
