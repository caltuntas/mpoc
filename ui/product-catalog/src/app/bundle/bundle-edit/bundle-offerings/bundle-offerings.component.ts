import { Component, OnInit, Input } from "@angular/core";
import { BundleModel } from "../../model/bundle-model";
import { BundleService } from "../../bundle.service";
import { IdNameDescriptionModel } from "../../model/idNameDescriptionModel";


@Component({
  selector: "app-bundle-offerings",
  templateUrl: "./bundle-offerings.component.html"
})
export class BundleOfferingsComponent implements OnInit {
  
  simpleOfferings:Array<IdNameDescriptionModel>;

  @Input() model: BundleModel;
  constructor(
    private bundleService:BundleService
   
  ) {
   
  }


  ngOnInit() {
    this.bundleService.getSimgpleOfferingsForSelect().subscribe(data=>this.simpleOfferings=data);

  }



  dtOptions = {
    dom: "Bfrtip",
    ajax: (data, callback, settings) => {
      this.bundleService
        .getSimgpleOfferingsForSelect()       
        .subscribe(data => {
          callback({
            aaData: data
          });
        });
    },
    columns: [
      { data: "id" },
      { data: "name" },
      { data: "description" },
      {
        render: (data, type, fullRow, meta) => {
          return "<button class='btn btn-info btn-sm pull-right'  offering-id='${fullRow.id}'><i class='fa fa-fw fa-plus'></i></button>";
        }
      }
    ],
    order: [[0, "desc"]]
  };



}
