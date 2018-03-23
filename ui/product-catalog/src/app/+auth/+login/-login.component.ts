// import { Component, OnInit } from '@angular/core';
// import {Router} from "@angular/router";
// import { HttpClient } from '@angular/common/http';
// import { AuthService } from 'app/+auth/auth.service';

// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html'
// })
// export class LoginComponent implements OnInit {
  
//   credentials = {username: '', password: ''};
  
//   //constructor(private router: Router) { }
//   constructor(private app: AuthService, private http: HttpClient, private router: Router) {
//   }

//   ngOnInit() {
//   }

//   login(event){
//     //event.preventDefault();
//     this.router.navigate(['/home'])
//   }
  
//   // login() {
//   //       this.app.authenticate(this.credentials, () => {
//   //       this.router.navigateByUrl('/');
//   //   });
//   //   return false;
//   // }
// }