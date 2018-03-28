import { Component, OnInit } from '@angular/core';
import {DocumentService} from './document.service'
import {Observable} from "rxjs/Observable";
import {Document} from "./detail/document"
import {NavigationExtras, Router, Routes} from "@angular/router";
import {NotificationComponent} from "../shared/utils/NotificationComponent";

@Component({
  selector: 'app-Document',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.css']
})
export class DocumentComponent implements OnInit {

  public scs:  Array<Document> = [];
  scso: Observable<Document[]>;
  public options = {
                ajax: (data, callback, settings) => {
                  this.scService.getDocuments()
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
                      "className": "centerCell",
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
  
  constructor(private router: Router,private scService: DocumentService,private notificationComponent: NotificationComponent) { }

  ngOnInit() {
    //this.getDocuments();
    
  }
  

  
  
  ngAfterViewInit() {
        document.querySelector('body').addEventListener('click', (event) => {
            let target = <Element>event.target;

            if (target.tagName.toLowerCase() === 'a' && jQuery(target).hasClass('sa-datatables-edit')) {
                this.onEditDocument(target.getAttribute('scl-id'));
            }
            if (target.tagName.toLowerCase() === 'a' && jQuery(target).hasClass('sa-datatables-delete')) {
                this.onDeleteDocument(target.getAttribute('scl-id'));
            }
        });
    }
  
  onEditDocument(scid) {
        console.log("edit document ch:", scid);

//        let navigationExtras: NavigationExtras = {
//            queryParams: {
//                "offeringId": scid
//            }
//        };

        this.router.navigate(['/document/detail/' + scid]);
    }

    onDeleteDocument(scid) {
        console.log("Delete Document", scid, "?");
      
        this.scService.deleteDocument(scid).subscribe((data) => {
          
          this.notificationComponent.showNotification("Delete","Deleted successfully");
          
            this.reloadPage();
        });
    }
  
  
  reloadPage() {
        this.router.routeReuseStrategy.shouldReuseRoute = function(){return false;};
        let currentUrl = this.router.url + '?';
        this.router.navigateByUrl(currentUrl)
            .then(() => {
                this.router.navigated = false;
                this.router.navigate([this.router.url]);
            });
    }

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
  