import {Injectable} from '@angular/core';
import {Catalog} from '../model/catalog.model';
import {Observable} from 'rxjs/Observable';
import {HttpClientProvider} from '../../shared/httpclientprovider/http-client-provider';

@Injectable()
export class CataloglistService {

    constructor(private http: HttpClientProvider) {
    }

    getAllCatalogs(): Observable<Catalog[]> {
        return this.http.get(`/catalog/getallcatalogs`);
    }

    createCatalog(model) {
        return this.http.post(`/catalog/createcatalog`, model);
    }
}
