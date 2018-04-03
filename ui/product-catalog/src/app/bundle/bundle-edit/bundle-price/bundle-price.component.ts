import { Component, OnInit, Input } from "@angular/core";
import { BundleModel } from "../../model/bundle-model";


@Component({
  selector: "app-bundle-price",
  templateUrl: "./bundle-price.component.html"
})
export class BundlePriceComponent implements OnInit {
  @Input() model: BundleModel;
  constructor(
   
  ) {
   
  }


  ngOnInit() {
    
  }


}
