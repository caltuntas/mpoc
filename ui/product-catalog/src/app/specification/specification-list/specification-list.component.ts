import {
  ChangeDetectorRef,
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild
} from "@angular/core";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";
import { specificationService } from "../specification.service";
import { DatatableComponent } from "../../shared/ui/datatable/datatable.component";
import { productSpecCharModel } from "../model/productSpecCharModel";

@Component({
  selector: "app-specification-list",
  templateUrl: "./specification-list.component.html"
})
export class SpecificationListComponent implements OnInit {
  speces: Array<productSpecCharModel> = [];
  reRenderTable = false;

  @ViewChild(DatatableComponent) specTable: DatatableComponent;

  options = {
    dom: "Bfrtip",
    ajax: (data, callback, settings) => {
      this.service
        .getSpecifications()
        .catch(this.handleError)
        .subscribe(data => {
          callback({
            aaData: data
          });
        });
    },
    columns: [
      { data: "id" },
      { data: "name" },
      { data: "code" },
      { data: "description" },
      { data: "createTime" },
      {
        render: (data, type, fullRow, meta) => {
          return `
                    <div class='btn-group dropdown pull-right show'><button class='btn btn-info btn-sm dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>
                        <i class='fa fa-gear fa-lg'></i></button>
                        <ul class='dropdown-menu  ng-star-inserted'>                                
                            <li>  
                              <a class='sa-datatables-edit-specification' spec-id='${fullRow.id}'>
                              <i class="fa fa-fw fa-edit text-muted hidden-md hidden-sm hidden-xs" style="color:cornflowerblue"></i>
                              Edit</a>
                        </li>
                            <li> <a class='sa-datatables-delete-specification' spec-id='${fullRow.id}'>
                            <i class="fa fa-fw fa-ban text-muted hidden-md hidden-sm hidden-xs" style="color:red"></i>
                            Delete
                        </a></li>
                        </ul>
                    </div>`;
        }
      }
    ],
    order: [[0, "desc"]]
  };



  constructor(
    private router: Router,
    private service: specificationService,
    private cdRef: ChangeDetectorRef
  ) {}

  ngOnInit() {}


  onEditSpec(specId) {
    this.router.navigate(["/specification/edit/" + specId]);
  }

  onDeleteSpec(specId) {
    
    this.service.deleteSpec(specId);
  }
  reloadOfferingListTable() {
    this.reRenderTable = true;
    this.cdRef.detectChanges();
    this.reRenderTable = false;
  }


  ngAfterViewInit() {
    document.querySelector("body").addEventListener("click", event => {
      let target = <Element>event.target;

      if (
        target.tagName.toLowerCase() === "a" &&
        jQuery(target).hasClass("sa-datatables-edit-specification")
      ) {
        this.onEditSpec(target.getAttribute("spec-id"));
      }
      if (
        target.tagName.toLowerCase() === "a" &&
        jQuery(target).hasClass("sa-datatables-delete-specification")
      ) {
        this.onDeleteSpec(target.getAttribute("spec-id"));
      }
    });
  }


  private handleError(error: any) {
    let errMsg = error.message
      ? error.message
      : error.status ? `${error.status} - ${error.statusText}` : "Server error";
    console.error(errMsg); // log to console instead
    return Observable.throw(errMsg);
  }
}
