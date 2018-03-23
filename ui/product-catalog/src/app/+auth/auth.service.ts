/*import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable()
export class AuthService {
  // store the URL so we can redirect after logging in
  redirectUrl: string;

  constructor(private http: HttpClient) {
  }

  authenticate(credentials, callback) {
      const headers = new HttpHeaders(credentials ? {
          'content-type' : 'application/x-www-form-urlencoded'
      } : {});
      const params = new HttpParams()
        .set('username', credentials['username'])
        .set('password', credentials['password']);

      this.http.post('login', params.toString(), {headers: headers}).subscribe(response => {
          this.isLoggedIn = true;
          if (callback) { callback(); }
      }, () => {
        this.isLoggedIn = false;
      });

  }

}*/

import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/delay';

@Injectable()
export class AuthService {
  isLoggedIn = false;

  // store the URL so we can redirect after logging in
  redirectUrl: string;

  login(): Observable<boolean> {
    return Observable.of(true).delay(1000).do(val => this.isLoggedIn = true);
  }

  logout(): void {
    this.isLoggedIn = false;
  }
}