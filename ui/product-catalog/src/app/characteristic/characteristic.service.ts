import {Injectable} from "@angular/core";
import {CharacteristicListModel} from "./model/characteristicListModel";
import {Observable} from "rxjs/Observable";
import {HttpClientProvider} from "../shared/httpclientprovider/http-client-provider";

@Injectable()
export class CharacteristicService{

    constructor(private http: HttpClientProvider) {
    }

    createCharacteristic(model) {
        return this.http.post(`/productspeccharacteristic/createproductspeccharacteristic`, model);
    }


    getAllCharacteristics(): Observable<CharacteristicListModel[]> {
        return this.http.get(`/productspeccharacteristic/getallcharacteristics`);
    }

    deleteCharacteristic(id) {
        return this.http.get('/productspeccharacteristic/deleteproductspeccharacteristic/' + id);
    }
}