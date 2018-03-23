import { Component }   from '@angular/core';
import { Router }      from '@angular/router';
import { AuthService } from '../auth.service';

// @Component({
//   template: `
//     <h2>LOGIN</h2>
//     <p>{{message}}</p>
//     <p>
//       <button (click)="login()"  *ngIf="!authService.isLoggedIn">Login</button>
//       <button (click)="logout()" *ngIf="authService.isLoggedIn">Logout</button>
//     </p>`
// })
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  message: string;
  credentials = {username: '', password: ''};
  
  constructor(public authService: AuthService, public router: Router) {
    this.setMessage();
  }

  setMessage() {
    this.message = 'Logged ' + (this.authService.isLoggedIn ? 'in' : 'out');
  }

  login(event) {
    this.message = 'Trying to log in ...';

    this.authService.login().subscribe(() => {
      this.setMessage();
      if (this.authService.isLoggedIn) {
        // Get the redirect URL from our auth service
        // If no redirect has been set, use the default
        let redirect = this.authService.redirectUrl ? this.authService.redirectUrl : '/home';

        // Redirect the user
        this.router.navigate([redirect]);
      }
    });
  }/*
  login2() {
    this.authService.authenticate(this.credentials, () => {
        this.router.navigateByUrl('/');
    });
    return false;
  }*/
  login3(event){
    //event.preventDefault();
    this.router.navigate(['/home'])
  }
  logout() {
    this.authService.logout();
    this.setMessage();
  }
}