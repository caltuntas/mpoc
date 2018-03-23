import {Injectable} from '@angular/core';
import {HttpClientProvider} from '../shared/httpclientprovider/http-client-provider';
import {Catalog} from './model/catalog.model';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class CatalogService {
    constructor(private http: HttpClientProvider) {
    }

    getAllCatalogs(): Observable<Catalog[]> {
        return this.http.get(`/catalog/getallcatalogs`);
    }

    createCatalog(model) {
        return this.http.post(`/catalog/createcatalog`, model);
    }
}
