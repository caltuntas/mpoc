import {ChangeDetectorRef, Component, ComponentFactoryResolver, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Router} from "@angular/router";
import {DatatableComponent} from "../../shared/ui/datatable/datatable.component";
import {OfferingService} from "../offering.service";

@Component({
    selector: 'app-offering-list',
    templateUrl: './offering-list.component.html',
    styleUrls: ['./offering-list.component.css']
})
export class OfferingListComponent implements OnInit, OnDestroy {

    reRenderTable = false;

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
            {"data": "isSellable"},
            {"data": "productSpesificationCode"},
            {"data": "catalogCode"},
            {
                "data": "validForStartDate",
                "render": function (data, type, full, meta) {
                    return data == null ? "" : data;
                }
            },
            {
                "data": "validForEndDate",
                "render": function (data, type, full, meta) {
                    return data == null ? "" : data;
                }
            },
            {
                render: (data, type, fullRow, meta) => {
                    return `
                        <div class='btn-group dropdown show pull-right'><button class='btn btn-info btn-sm dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>
                            <i class='fa fa-gear fa-lg'></i></button>
                            <ul class='dropdown-menu  ng-star-inserted'>                                
                                <li>
                                
                                    <a class="sa-datatables-edit-offering"  offering-id='${fullRow.id}'>
                                        <i class="fa fa-fw fa-edit text-muted hidden-md hidden-sm hidden-xs" style="color:cornflowerblue"></i>
                                            Edit
                                    </a>
                                    
                                </li>
                                <li>
                                    <a class="sa-datatables-delete-offering"  offering-id='${fullRow.id}'>
                                        <i class="fa fa-fw fa-ban text-muted hidden-md hidden-sm hidden-xs" style="color:red"></i>
                                            Delete
                                    </a>
                                </li>
                            </ul>
                        </div>`;
                }
            }],
        order: [[0, "desc"]]

    };

    constructor(private router: Router,
                private offeringService: OfferingService,
                private cdRef: ChangeDetectorRef) {
    }

    ngAfterViewInit() {
        document.querySelector('body').addEventListener('click', (event) => {
            let target = <Element>event.target;

            if (target.tagName.toLowerCase() === 'a' && jQuery(target).hasClass('sa-datatables-edit-offering')) {
                this.onEditOffering(target.getAttribute('offering-id'));
            }
            if (target.tagName.toLowerCase() === 'a' && jQuery(target).hasClass('sa-datatables-delete-offering')) {
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
        if (!this.cdRef['destroyed']) {
            this.cdRef.detectChanges();
        }
        this.reRenderTable = false;
    }

    ngOnInit() {

    }

    ngOnDestroy() {
        this.cdRef.detach();
    }

    private handleError(error: any) {
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }
}
