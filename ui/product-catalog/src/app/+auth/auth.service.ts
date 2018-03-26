/*  authenticate(credentials, callback) {
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
import { Router } from '@angular/router';
import { User } from 'app/+auth/user';

@Injectable()
export class AuthService {
  public isLoggedIn: boolean = false;

  // store the URL so we can redirect after logging in
  redirectUrl: string;

  users={
    admin: '12345'
  }

  constructor(public router: Router) {
  }

  login(username: any, password: any) {
    if(username && password && this.users[username] && this.users[username]==password){
      this.isLoggedIn = true;
      let user = new User();
      user.username = username;
      user.password = password;
      user.id = 1;
      user.firstName = "Admin";
      user.lastName = "LastName";
      localStorage.setItem('currentUser', JSON.stringify(user));
      this.router.navigate(this.redirectUrl ? [this.redirectUrl] : ['/home']);
    }
    else{
      console.log('\n', 'user credentials are not valid', '\n\n');
    }
  }

  logout(): void {
    this.isLoggedIn = false;
    localStorage.clear();
    this.router.navigate(['/auth/login']);
  }
}