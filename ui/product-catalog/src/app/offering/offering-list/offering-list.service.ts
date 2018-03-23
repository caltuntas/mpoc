import {Injectable} from '@angular/core';
import {Offering} from "../model/offering.model";
import {Observable} from "rxjs/Observable";
import {HttpClientProvider} from "../../shared/httpclientprovider/http-client-provider";

@Injectable()
export class OfferingListService {

    constructor(private http: HttpClientProvider) {
    }

    getOfferings(): Observable<Offering[]> {
        return this.http.get(`/productoffering/getallofferings`);
    }

    createOffering(model) {
        return this.http.post(`/productoffering/createoffering`, model);
    }
}
