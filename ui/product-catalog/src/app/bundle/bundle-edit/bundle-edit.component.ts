import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { SalesChannel } from "../../sales-channel/detail/sales-channel";
import { BundleModel } from "../model/bundle-model";
import { CharacteristicService } from "../../characteristic/characteristic.service";
import { ProdSpecCharValueUseListModel } from "../../characteristic/model/prod-spec-char-value-use-list.model";
@Component({
  selector: "app-bundle-edit",
  templateUrl: "./bundle-edit.component.html"
})
export class BundleEditComponent implements OnInit {
  model: BundleModel;
  charValueUseList: Array<ProdSpecCharValueUseListModel> = [];
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private charService: CharacteristicService
  ) {
    this.model = new BundleModel();
    const idParam = route.snapshot.params.offeringId;
    if (idParam) {
      this.model.id = idParam;
      this.model.isNewbundle = false;
    }
  }

  ngOnInit() {}

  ngAfterViewInit() {
    var self = this;

    //Catalog Select
    jQuery("#catalogSelect").on("select2:select", function(e) {
      var data = e.params.data;
      console.log(data.id);
      self.model.catalogId = jQuery("#catalogSelect").val();
    });
    //Catalog Select

    //Category Select
    jQuery("#categorySelect").on("select2:select", function(e) {
      var data = e.params.data;
      console.log(data.id);
      self.model.categoryId = jQuery("#categorySelect").val();
    });
    //Wizard Events
    jQuery("#offeringWizard").on("actionclicked.fu.wizard", function(
      event,
      data
    ) {
      // if (data.direction === "next") {
      //   if (!self.validateStep(data.step)) {
      //     event.preventDefault();
      //   }
      // }

      console.log(data.direction);
      console.log(data.step);
      console.log(data);
    });
    //Wizard Events
  }

  loadCharValueUses(specId) {
    this.charService.getSpecCharValueUses(specId).subscribe(charValuUseList => {
      this.charValueUseList = charValuUseList;
    });
  }
  onWizardComplete(data) {
    console.log("fuel-ux wizard complete", data);
    // this.model.salesChannels = this.selectedSalesChannels;
    // this.model.segments = this.selectedSegments;
    // this.model.documents = this.selectedDocuments;
    // this.model.productOfferingTypeId = 2;
    // this.model.simpleProductOfferingIds = [];
    // this.model.simpleProductOfferingIds.push(336);
    // this.model.simpleProductOfferingIds.push(337);

    // console.log(this.isNewOffering);
    // if (this.isNewOffering) {
    //     this.bundleService.createOffering(this.model).subscribe(data => {
    //         this.router.navigate(['/bundle/bundle-list']);
    //     });
    // } else {
    //     this.bundleService.updateOffering(this.model).subscribe(data => {
    //         this.router.navigate(['/bundle/bundle-list']);
    //     });
    // }
  }
}
