import {Injectable} from "@angular/core";
import {characteristicListModel} from "./model/characteristicListModel";
import {Observable} from "rxjs/Observable";
import {HttpClientProvider} from "../shared/httpclientprovider/http-client-provider";

@Injectable()
export class CharacteristicService{

    constructor(private http: HttpClientProvider) {
    }

    getAllCharacteristics(): Observable<characteristicListModel[]> {
        return this.http.get(`/productspeccharacteristic/getallcharacteristics`);
    }
}