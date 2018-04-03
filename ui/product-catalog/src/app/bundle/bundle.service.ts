import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClientProvider} from "../shared/httpclientprovider/http-client-provider";
import {BundleEditModel} from "./model/bundle-edit-model";
import {OfferingListModel} from "./model/bundle-list-model";
import { BundleModel } from './model/bundle-model';
import { IdNameDescriptionModel } from './model/idNameDescriptionModel';


@Injectable()
export class BundleService {

    constructor(private http: HttpClientProvider) {
    }

    

    getOfferings(): Observable<OfferingListModel[]> {
        return this.http.get(`/productoffering/getOfferings/2`);
    }

    // getSimpleOfferingsForSelect2(): Observable<IdNameDescriptionModel[]> {
    //     return  this.http.get2(`/productoffering/getSimgpleOfferingsForSelect`);
    // }
    getSimpleOfferingsForSelect(): Observable<IdNameDescriptionModel[]> {
        return  this.http.get(`/productoffering/getSimgpleOfferingsForSelect`);
    }
    createOffering(model) {
        return this.http.post(`/productoffering/createoffering`, model);
    }

    deleteOffering(id) {
        return this.http.get('/productoffering/deleteoffering/' + id);
    }

    getOffering(id) : Observable<BundleModel> {
        return this.http.get(`/productoffering/getoffering/` + id);
    }

    updateOffering(model){
        return this.http.post(`/productoffering/updateoffering`, model);
    }
}
