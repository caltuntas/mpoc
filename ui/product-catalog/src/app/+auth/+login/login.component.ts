import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'app/+auth/auth.service';
import { LoginRequestModel } from '../model/login-request-model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  public username;
  public password;
  message: string;
  public model: LoginRequestModel;
  constructor(private authService: AuthService) {
    this.model = new LoginRequestModel();
  }

  login(event) {
    event.preventDefault();
    let isSucessfull = this.authService.login(this.model);
    if (!isSucessfull) {
      this.message = "Credentials are not valid!";
    }
  }
  logout() {
    this.authService.logout();
    this.message = "Logged out!";
  }
}