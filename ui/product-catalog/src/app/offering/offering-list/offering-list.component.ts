import {ChangeDetectorRef, Component, ComponentFactoryResolver, OnInit, ViewChild} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Router} from "@angular/router";
import {Offering} from "../model/offering.model";
import {DatatableComponent} from "../../shared/ui/datatable/datatable.component";
import {OfferingService} from "../offering.service";

@Component({
    selector: 'app-offering-list',
    templateUrl: './offering-list.component.html',
    styleUrls: ['./offering-list.component.css']
})
export class OfferingListComponent implements OnInit {

    offerings: Array<Offering> = [];
    reRenderTable = false;

    @ViewChild(DatatableComponent) offeringTable: DatatableComponent;

    options = {
        dom: "Bfrtip",
        ajax: (data, callback, settings) => {
            this.offeringService.getOfferings()
                .catch(this.handleError)
                .subscribe((data) => {
                    callback({
                        aaData: data
                    })
                })
        },
        columns: [
            {"data": "id"},
            {"data": "name"},
            {"data": "description"},
            {
                "data": "validFor.validForEndDate",
                "render": function (data, type, full, meta) {
                    return data == null ? "" : data;
                }
            },
            {"data": "isSellable"},
            {
                "data": "productSpecification.name",
                "render": function (data, type, full, meta) {
                    return data == null ? "" : data;
                }
            },
            {
                "data": "catalog.name",
                "render": function (data, type, full, meta) {
                    return data == null ? "" : data;
                }
            },
            {
                render: (data, type, fullRow, meta) => {
                    return `
                        <div class='btn-group dropdown show'><button class='btn btn-info btn-sm dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>
                            <i class='fa fa-gear fa-lg'></i></button>
                            <ul class='dropdown-menu  ng-star-inserted'>                                
                                <li><a class='sa-datatables-edit' offering-id='${fullRow.id}'>Edit</a></li>
                                <li><a class='sa-datatables-delete' offering-id='${fullRow.id}'>Delete</a></li>
                            </ul>
                        </div>`;
                }
            }],

    };

    ngAfterViewInit() {
        document.querySelector('body').addEventListener('click', (event) => {
            let target = <Element>event.target;

            if (target.tagName.toLowerCase() === 'a' && jQuery(target).hasClass('sa-datatables-edit')) {
                this.onEditOffering(target.getAttribute('offering-id'));
            }
            if (target.tagName.toLowerCase() === 'a' && jQuery(target).hasClass('sa-datatables-delete')) {
                this.onDeleteOffering(target.getAttribute('offering-id'));
            }
        });

    }

    onEditOffering(offeringId) {
        console.log("edit offering:", offeringId);
        this.router.navigate(['/offering/offering-edit/' + offeringId]);
    }

    onDeleteOffering(offeringId) {
        console.log("Delete offering", offeringId, "?");
        this.offeringService.deleteOffering(offeringId).subscribe((data) => {
            this.reloadOfferingListTable();
        });
    }

    reloadOfferingListTable() {
        this.reRenderTable = true;
        this.cdRef.detectChanges();
        this.reRenderTable = false;
    }

    constructor(private router: Router, private offeringService: OfferingService, private cdRef: ChangeDetectorRef) {
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
