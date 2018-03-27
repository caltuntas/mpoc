import {Injectable} from '@angular/core';
import {HttpClientProvider} from '../shared/httpclientprovider/http-client-provider';
import {Category} from './category.model';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class CategoryService {

    constructor(private http: HttpClientProvider) {
    }

    getAll(): Observable<Category[]> {
        return this.http.get(`/category`);
    }

    get(id: any): Observable<Category> {
        return this.http.get(`/category/`+ id);
    }

    create(model) {
        return this.http.post(`/category`, model);
    }

    update(id: any, model): any {
        return this.http.put(`/category/`+ id, model);
    }

    delete(id: any): any {
        return this.http.delete(`/category/`+ id);
    }
}
