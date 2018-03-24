import { NgModule, ApplicationRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';

import { routing } from './app.routing'
// App is our top level component
import { AppComponent } from './app.component';
import { APP_RESOLVER_PROVIDERS } from './app.resolver';
import { AppState, InternalStateType } from './app.service';

// Core providers
import {CoreModule} from "./core/core.module";
import {SmartadminLayoutModule} from "./shared/layout/layout.module";
import { ModalModule } from 'ngx-bootstrap/modal';
import {HttpClient} from "@angular/common/http";

import { Router } from '@angular/router';
import { AuthGuard } from './+auth/auth-guard.service'
import { AuthService } from './+auth/auth.service'
import { LoginRoutingModule } from './+auth/+login/login-routing.module';
import { LoginComponent } from './+auth/+login/login.component';


// Application wide providers
const APP_PROVIDERS = [
  ...APP_RESOLVER_PROVIDERS,
  AppState
];

type StoreType = {
  state: InternalStateType,
  restoreInputValues: () => void,
  disposeOldHosts: () => void
};

@NgModule({
  bootstrap: [ AppComponent ],
  declarations: [
    AppComponent,
    LoginComponent,
    //PageNotFoundComponent
  ],
  imports: [ 
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ModalModule.forRoot(),
    CoreModule,
    SmartadminLayoutModule,
    routing,
    LoginRoutingModule,
    FormsModule,
  ],
  exports: [
  ],
  providers: [ 
    // ENV_PROVIDERS,
    APP_PROVIDERS,
    AuthGuard, 
    AuthService,
    // DialogService
  ]
})
export class AppModule {
  constructor(public appRef: ApplicationRef, public appState: AppState) {}

// // Diagnostic only: inspect router configuration
// constructor(router: Router ) {
//   console.log('Routes: ', JSON.stringify(router.config, undefined, 2));
// }
}

