import { Component }   from '@angular/core';
import { Router }      from '@angular/router';
import { AuthService } from 'app/+auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  public username;
  public password;
  message: string;
  constructor(private authService: AuthService){}
  ngOnInit(){}

/*

  login4(event) {
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
  }
  login2() {
    this.authService.authenticate(this.credentials, () => {
        this.router.navigateByUrl('/');
    });
    return false;
  }*/
  login(event){
    event.preventDefault();
    this.authService.login(this.username, this.password);
    if (!this.authService.isLoggedIn){
      this.message = "Credentials are not valid!";
    }    
  }
  logout() {
    this.authService.logout();
    this.message = "Logged out!";
  }
}