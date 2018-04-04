import {
  Component,
  OnInit,
  Input,
  ChangeDetectorRef,
  OnDestroy
} from "@angular/core";
import { BundleModel } from "../../model/bundle-model";
import { BundleService } from "../../bundle.service";
import { IdNameDescriptionModel } from "../../model/idNameDescriptionModel";
import { Response } from "@angular/http";

@Component({
  selector: "app-bundle-offerings",
  templateUrl: "./bundle-offerings.component.html"
})
export class BundleOfferingsComponent implements OnInit {
  offerings: Array<IdNameDescriptionModel>;
  reRenderTable = false;

  @Input() model: BundleModel;

  constructor(
    private bundleService: BundleService,
    private cdRef: ChangeDetectorRef
  ) {

    
  }

  async ngOnInit() {
    this.model.simpleProductOfferingIds = [];
    // let data = await this.bundleService.getSimpleOfferingsForSelect().toPromise<IdNameDescriptionModel[]>();
    // this.offerings=data;
    // console.log(this.offerings);
  }
  



  dtOptions = {
    dom: "Bfrtip",
    ajax: (data, callback, settings) => {
      this.bundleService.getSimpleOfferingsForSelect().subscribe(data => {
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
          return `<a class='btn btn-success btn-sm pull-right sa-datatables-add-offering'  offering-id='${
            fullRow.id
          }'><i class='fa fa-fw fa-plus'></i></a>`;
        }
      }
    ],
    order: [[0, "desc"]]
  };

  ngAfterViewInit() {
    document.querySelector("body").addEventListener("click", event => {
      let target = <Element>event.target;

      if (
        target.tagName.toLowerCase() === "a" &&
        jQuery(target).hasClass("sa-datatables-add-offering")
      ) {
        this.onAddOffering(target.getAttribute("offering-id"));
      }
    });
    // console.log(this.offerings);
  }

  onAddOffering(offeringId) {
    var element = jQuery("a[offering-id='" + offeringId + "']");
//console.log(offeringId);
    if (element.hasClass("btn-success")) {
      element.removeClass("btn-success");
      element.addClass("btn-danger");
      element.html("<i class='fa fa-fw fa-minus'></i>");
      this.model.simpleProductOfferingIds.push(offeringId);
    } else {
      element.removeClass("btn-danger");
      element.addClass("btn-success");
      element.html("<i class='fa fa-fw fa-plus'></i>");
      this.model.simpleProductOfferingIds = this.model.simpleProductOfferingIds.filter(
        x => x != offeringId
      );
    }
  }
  reloadSpecListTable() {
    this.reRenderTable = true;
    if (!this.cdRef["destroyed"]) {
      this.cdRef.detectChanges();
    }
    this.reRenderTable = false;
  }
}
