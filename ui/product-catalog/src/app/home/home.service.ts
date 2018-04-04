import { Injectable } from '@angular/core';
import { Observable } from "rxjs/Observable";
import { HttpClientProvider } from "../shared/httpclientprovider/http-client-provider";
import { HomeChartsData } from './model/homeChartData';

@Injectable()
export class HomeService {

    constructor(private http: HttpClientProvider) { }

    getChartsData(): Observable<HomeChartsData[]> {
        return this.http.get(`/home/getChartsData`);
    }

    getOfferingSegments(): Observable<HomeChartsData[]> {
        return this.http.get(`/home/getOfferingSegments`);
    }

}
