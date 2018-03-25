import { Injectable } from "@angular/core";
import { characteristicListModel } from "./model/characteristicListModel";
import { Response, Http } from "@angular/http";
import {Observable} from "rxjs/Observable";
import {HttpClientProvider} from "../shared/httpclientprovider/http-client-provider";

@Injectable()
export class CharacteristicService{

    // private _postsURL = "https://jsonplaceholder.typicode.com/posts";

    constructor(private http: HttpClientProvider) {
    }

    getAllCharacteristics(): Observable<characteristicListModel[]> {
        return this.http
            .get(`/productoffering/getallofferings`)
            .map((response: Response) => {
                return <characteristicListModel[]>response.json();
            });
    }
}