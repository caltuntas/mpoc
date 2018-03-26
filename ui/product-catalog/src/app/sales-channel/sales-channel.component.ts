import { Component, OnInit } from '@angular/core';
import {SalesChannelService} from './sales-channel.service'
import {Observable} from "rxjs/Observable";
import {SalesChannel} from "./detail/sales-channel"
import {NavigationExtras, Router, Routes} from "@angular/router";

@Component({
  selector: 'app-salesChannel',
  templateUrl: './sales-channel.component.html',
  styleUrls: ['./sales-channel.component.css']
})
export class SalesChannelComponent implements OnInit {

  public scs:  Array<SalesChannel> = [];
  scso: Observable<SalesChannel[]>;
  public options = {
                ajax: (data, callback, settings) => {
                  this.scService.getSalesChannels()
                  .catch(this.handleError)
                  .subscribe((data) => {
                      callback({
                          aaData: data
                      })
                  })
                },
                "iDisplayLength": 15,
                "columns": [
                     { "data": "id" },
                      { "data": "code" },
                      { "data": "name" },
                     { 
                       "orderable":false,
                       "render": (data, type, fullRow, meta) => {
                         return `
                            <div class='btn-group dropdown show'><button class='btn btn-info btn-sm dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>
                                <i class='fa fa-gear fa-lg'></i></button>
                                <ul class='dropdown-menu  ng-star-inserted'>                                
                                    <li><a class='sa-datatables-edit' scl-id='${fullRow.id}'>Edit</a></li>
                                    <li><a class='sa-datatables-delete' scl-id='${fullRow.id}'>Delete</a></li>
                                </ul>
                            </div>`;
                }
                }],
                "order": [[1, 'asc']]
  }
  
  constructor(private router: Router,private scService: SalesChannelService) { }

  ngOnInit() {
    //this.getSalesChannels();
    
  }
  

  
  
  ngAfterViewInit() {
        document.querySelector('body').addEventListener('click', (event) => {
            let target = <Element>event.target;

            if (target.tagName.toLowerCase() === 'a' && jQuery(target).hasClass('sa-datatables-edit')) {
                this.onEditSalesCh(target.getAttribute('scl-id'));
            }
            if (target.tagName.toLowerCase() === 'a' && jQuery(target).hasClass('sa-datatables-delete')) {
                this.onDeleteSalesCh(target.getAttribute('scl-id'));
            }
        });
    }
  
  onEditSalesCh(scid) {
        console.log("edit sales ch:", scid);

        let navigationExtras: NavigationExtras = {
            queryParams: {
                "offeringId": scid
            }
        };

        this.router.navigate(['/sales-channel/detail/' + scid]);
    }

    onDeleteSalesCh(scid) {
        console.log("Delete salesch", scid, "?");
        this.scService.deleteSalesChannel(scid).subscribe((data) => {
            window.location.reload();
        });
    }
  
  
//  public options = {
//    //"ajax": {"url": 'http://localhost:8080/saleschannel/getallsaleschannels', "dataSrc": "tableData"},//'/assets/api/tables/salechannels.json',
//    "data" : this.scs,
//    "iDisplayLength": 15,
//    "columns": [
//     { "data": "id" },
//      { "data": "code" },
//      { "data": "description" },
//      {"orderable":false,
//        "render": function (data, type, full, meta) {return " <a routerlink='/detail/"+full.id+"' href='#/sales-channel/detail/"+full.id+"' class='btn btn-primary'><i class='fa fa-edit'></i> </a><a (click)='(null)' class='btn btn-danger'><i class='fa fa-remove'></i> </a>";}
//        }
// 
//    ],
//    "order": [[1, 'asc']]
//  }

  public lastColumn(d) {

    return "";
  }
  
  private handleError(error: any) {
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }

}
  