import { Component, OnInit, Input } from "@angular/core";
import { BundleModel } from "../../model/bundle-model";

@Component({
  selector: "app-bundle-info",
  templateUrl: "./bundle-info.component.html"
})
export class BundleInfoComponent implements OnInit {
  termValues;
  @Input() model: BundleModel;
  constructor() {}

  ngOnInit() {
    this.loadTerms();
  }

  loadTerms() {
    this.termValues = [
      { value: "Please Select", id: 0 },
      { value: 6, id: 1 },
      { value: 12, id: 2 },
      { value: 18, id: 3 },
      { value: 24, id: 4 },
      { value: 30, id: 5 },
      { value: 36, id: 6 }
    ];
  }
}
