import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {OfferingListService} from './offering/offering-list/offering-list.service';
import {HttpClientProvider} from './shared/httpclientprovider/http-client-provider';
import {CataloglistService} from './catalog/catalog-list/catalog-list.service';


@Injectable()
export class DataResolver implements Resolve<any> {
  constructor() {

  }
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return Observable.of({ res: 'I am data'});
  }
}

// an array of services to resolve routes with data
export const APP_RESOLVER_PROVIDERS = [
  DataResolver,
  HttpClientProvider,
  OfferingListService,
    CataloglistService
];
