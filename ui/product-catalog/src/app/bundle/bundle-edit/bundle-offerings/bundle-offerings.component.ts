import { Component, OnInit, Input } from "@angular/core";
import { BundleModel } from "../../model/bundle-model";


@Component({
  selector: "app-bundle-offerings",
  templateUrl: "./bundle-offerings.component.html"
})
export class BundleOfferingsComponent implements OnInit {
  @Input() model: BundleModel;
  constructor(
   
  ) {
   
  }


  ngOnInit() {
    
  }


}
