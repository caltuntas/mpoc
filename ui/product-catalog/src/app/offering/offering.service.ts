import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Offering} from "./model/offering.model";
import {HttpClientProvider} from "../shared/httpclientprovider/http-client-provider";

@Injectable()
export class OfferingService {

    constructor(private http: HttpClientProvider) {
    }

    getOfferings(): Observable<Offering[]> {
        return this.http.get(`/productoffering/getallofferings`);
    }

    createOffering(model) {
        return this.http.post(`/productoffering/createoffering`, model);
    }

    deleteOffering(id) {
        return this.http.get('/productoffering/deleteOffering/' + id);
    }
}
