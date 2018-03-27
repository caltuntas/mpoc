import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClientProvider} from "../httpclientprovider/http-client-provider";
import {Offering} from "../model/offering/offering.model";

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
