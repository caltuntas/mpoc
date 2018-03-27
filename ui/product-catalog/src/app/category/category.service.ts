import {Injectable} from '@angular/core';
import {HttpClientProvider} from '../shared/httpclientprovider/http-client-provider';
import {Category} from './model/category.model';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class CategoryService {

    constructor(private http: HttpClientProvider) {
    }

    getAll(): Observable<Category[]> {
        return this.http.get(`/category`);
    }

    create(model) {
        return this.http.post(`/category`, model);
    }

    edit(id: any, model): any {
        return this.http.put(`/category/`, id);
    }

    delete(id: any): any {
        return this.http.delete(`/category/`+ id);
    }
}
