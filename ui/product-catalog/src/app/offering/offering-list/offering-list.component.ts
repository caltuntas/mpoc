import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {OfferingService} from "../offering-service";

@Component({
    selector: 'app-offering-list',
    templateUrl: './offering-list.component.html',
    styleUrls: ['./offering-list.component.css']
})
export class OfferingListComponent implements OnInit {
    options = {
        dom: "Bfrtip",
        ajax: (data, callback, settings) => {
            this.offeringService.getOfferings()
                .map((data: any) => (data.data || data))
                .catch(this.handleError)
                .subscribe((data) => {
                    callback({
                        aaData: data.slice(0, 100)
                    })
                })
        },
        columns: [
            {"data": "id"},
            {"data": "name"},
            {"data": "description"},
            {
                render: (data, type, fullRow, meta) => {
                    return `                              
                              <button class="btn btn-xs btn-info sa-datatables-edit" offering-id="${fullRow.id}">edit</button>
                              <button class="btn btn-xs btn-danger sa-datatables-delete" offering-id="${fullRow.id}">delete</button>
                          `;
                }
            }]
    };

    ngAfterViewInit() {
        document.querySelector('body').addEventListener('click', (event) => {
            let target = <Element>event.target;

            if (target.tagName.toLowerCase() === 'button' && jQuery(target).hasClass('sa-datatables-edit')) {
                this.onEditOffering(target.getAttribute('offering-id'));
            }
            if (target.tagName.toLowerCase() === 'button' && jQuery(target).hasClass('sa-datatables-delete')) {
                this.onDeleteOffering(target.getAttribute('offering-id'));
            }
        });
    }


    onEditOffering(offeringId) {
        console.log("edit offering:", offeringId);
    }

    onDeleteOffering(offeringId) {
        console.log("Delete offering", offeringId, "?");
        this.offeringService.deleteOffering(offeringId).subscribe();
    }

    constructor(private offeringService: OfferingService) {
    }

    ngOnInit() {

    }
    private handleError(error: any) {
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }
}
