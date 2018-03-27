import {Injectable} from '@angular/core';
import {Offering} from "./model/offering.model";
import {Observable} from "rxjs/Observable";
import {HttpClientProvider} from "../shared/httpclientprovider/http-client-provider";
import { productSpecCharModel } from './model/productSpecCharModel';

@Injectable()
export class specificationService {

    constructor(private http: HttpClientProvider) {
    }
    getCharacteristics(): Observable<Array<productSpecCharModel>> {
        return this.http.get(`/productspec/getCharacteristics`);
    }

    createSpec(model) {
        return this.http.post(`/productspec/createSpec`, model);
    }
    getSpecifications(){
        return this.http.get(`/productspec/getAll`);
    }
    
}