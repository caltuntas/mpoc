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
import { NotificationComponent } from "../../shared/utils/NotificationComponent";

@Component({
  selector: "app-specification-list",
  templateUrl: "./specification-list.component.html"
})
export class SpecificationListComponent implements OnInit {
  speces: Array<productSpecCharModel> = [];
  reRenderTable = false;

  @ViewChild(DatatableComponent) specTable: DatatableComponent;

  constructor(
    private router: Router,
    private service: specificationService,
    private cdRef: ChangeDetectorRef,
    private notificationComponent: NotificationComponent
  ) {}

  options = {
    dom: "Bfrtip",
    ajax: (data, callback, settings) => {
      this.service
        .getSpecifications()       
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
                            <a class='sa-datatables-edit-specification' spec-id='${
                              fullRow.id
                            }'>
                              <i class="fa fa-fw fa-edit text-muted hidden-md hidden-sm hidden-xs" style="color:cornflowerblue"></i>
                              Edit</a>
                        </li>
                            <li> <a class='sa-datatables-delete-specification' spec-id='${
                              fullRow.id
                            }'>
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

  ngOnInit() {

   
  }

  ngOnDestroy() {
    this.cdRef.detach();
  }

  onEditSpec(specId) {
    this.router.navigate(["/specification/edit/" + specId]);
  }

  onDeleteSpec(specId) {
    var r = confirm("Are you sure you want to delete this specification?");
    if (r == true) {
      this.service.deleteSpec(specId).subscribe(data => {
        this.reloadSpecListTable();
        this.notificationComponent.showNotification(
          "Specification",
          "Deleted successfully"
        );
      });

      this.service.deleteSpec(specId);
      this.reloadSpecListTable();
    }
  }

  reloadSpecListTable() {
    this.reRenderTable = true;
    if (!this.cdRef['destroyed']) {
        this.cdRef.detectChanges();
    }
    this.reRenderTable = false;
}


  ngAfterViewInit() {
    document.querySelector('body').addEventListener('click', (event) => {
      let target = <Element>event.target;

      if (target.tagName.toLowerCase() === 'a' && jQuery(target).hasClass('sa-datatables-edit-specification')) {
          this.onEditSpec(target.getAttribute('spec-id'));
      }
      if (target.tagName.toLowerCase() === 'a' && jQuery(target).hasClass('sa-datatables-delete-specification')) {
          this.onDeleteSpec(target.getAttribute('spec-id'));
      }
  });
  }

 
}
