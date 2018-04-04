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
  offerings: IdNameDescriptionModel[]=[];
  reRenderTable = false;
  selectedOfferings:number[]=[];

  @Input() model: BundleModel;

  constructor(
    private bundleService: BundleService,
    private cdRef: ChangeDetectorRef
  ) {}

  async ngOnInit() {
    this.model.simpleProductOfferingIds = [];
    let data = await this.bundleService.getSimpleOfferingsForSelectAsync().toPromise<IdNameDescriptionModel[]>();
   this.offerings=data;
   console.log(this.offerings);
  }

  dtOptions = {
    dom: "Bfrtip",
    ajax: (data, callback, settings) => {
      this.bundleService.getSimpleOfferingsForSelectAsync().subscribe(data => {
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
          return `<a  class='btn btn-success btn-sm pull-right sa-datatables-add-offering'  simple-offering-id='${
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
        this.onAddOffering(target.getAttribute("simple-offering-id"));
      }
    });
    // console.log(this.offerings);
  }

  onAddOffering(offeringId) {
    
    var addButton = jQuery("a[simple-offering-id='" + offeringId + "']");
   
      this.model.simpleProductOfferingIds.push(offeringId);
      this.selectedOfferings.push(offeringId);
      addButton.parent().parent().hide();
   
  }

  removeOffering(offeringId){
    var addButton = jQuery("a[simple-offering-id='" + offeringId + "']");
   
      
    this.model.simpleProductOfferingIds = this.model.simpleProductOfferingIds.filter(
      x => x != offeringId
    );
    this.selectedOfferings=this.selectedOfferings.filter(x=>x!=offeringId);
    addButton.parent().parent().show();

  }

  getNameOfOffering(offeringId):string{
    return this.offerings.filter(x=>x.id==offeringId)[0].name;
  }
  
}
