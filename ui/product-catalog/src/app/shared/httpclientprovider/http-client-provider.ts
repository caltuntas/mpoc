import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class HttpClientProvider {

    restUri: string;

    constructor(private http: HttpClient) {
        this.restUri = "http://localhost:8080";
    }

    request(method: string, path: string, params?: any, data?: any): Observable<any> {
        let url = "http://localhost:8080" + path;

        const options = {
            headers: {
                'Content-type': 'application/json'
            },
            body: data,
            params: params
        };

        switch (method) {
            case 'GET':
                return this.http.get(url, options);
            case 'POST':
                return this.http.post(url, data, options);
            case 'PUT':
                return this.http.put(url, data, options);
            case 'DELETE':
                return this.http.delete(url, options);
        }
    }

    get(path: string, params?: any): Observable<any> {
      return this.request('GET', path, params);
    }

    post(path: string, data: any): Observable<any> {
        return this.request('POST', path, null, data);
    }

    put(path: string, data: any): Observable<any> {
        return this.request('PUT', path, null, data);
    }

    delete(path: string, data?: any): Observable<any> {
        return this.request('DELETE', path, null, data);
    }
}
