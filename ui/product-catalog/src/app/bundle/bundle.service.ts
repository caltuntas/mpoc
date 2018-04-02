import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClientProvider} from "../shared/httpclientprovider/http-client-provider";
import {BundleEditModel} from "./model/bundle-edit-model";
import {OfferingListModel} from "./model/bundle-list-model";


@Injectable()
export class BundleService {

    constructor(private http: HttpClientProvider) {
    }

    getOfferings(): Observable<OfferingListModel[]> {
        return this.http.get(`/productoffering/getallofferings`);
    }

    getAllOfferingsByProductOfferingTypeId(): Observable<OfferingListModel[]> {
        return this.http.get(`/productoffering/productOfferingTypeId=1`);
    }

    createOffering(model) {
        return this.http.post(`/productoffering/createoffering`, model);
    }

    deleteOffering(id) {
        return this.http.get('/productoffering/deleteoffering/' + id);
    }

    getOffering(id) : Observable<BundleEditModel> {
        return this.http.get(`/productoffering/getoffering/` + id);
    }

    updateOffering(model){
        return this.http.post(`/productoffering/updateoffering`, model);
    }
}
