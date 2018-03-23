import {Component, OnInit} from '@angular/core';
import {CreateCatalogModel} from '../model/create-catalog-model';
import {CataloglistService} from './catalog-list.service';
import {Catalog} from '../model/catalog.model';

@Component({
    selector: 'app-catalog-list',
    templateUrl: './catalog-list.component.html'
})
export class CatalogListComponent implements OnInit {

    public options;
    public catalogs: Array<Catalog> = [];

    constructor(private catalogListService: CataloglistService) {
    }

    ngOnInit() {
        this.createCatalog();
        this.getAllCatalogs();
    }

    createCatalog() {
        const catalog =
            new CreateCatalogModel(
                'test',
                '2012-04-23T18:25:43.511Z',
                '2012-04-23T18:25:43.511Z',
                'desc',
                '1',
                1,
                false
            );

        this.catalogListService.createCatalog(catalog).subscribe();
    }

    getAllCatalogs() {
        this.catalogListService.getAllCatalogs().subscribe((catalogs) => {
            this.catalogs = catalogs;
            this.options = {
                'data': this.catalogs,
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

    public lastColumn(d) {

        return '';
    }

}
