import {Injectable} from '@angular/core';
import {HttpClientProvider} from "../shared/httpclientprovider/http-client-provider";
import {Observable} from "rxjs/Observable";
import {SalesChannel} from "./detail/sales-channel"

@Injectable()
export class SalesChannelService {

    constructor(private http: HttpClientProvider) {
    }

    getSalesChannels(): Observable<SalesChannel[]> {
        return this.http.get(`/saleschannel/getallsaleschannels`);
    }

//    createOffering(model) {
//        return this.http.post(`/productoffering/createoffering`, model);
//    }
}
