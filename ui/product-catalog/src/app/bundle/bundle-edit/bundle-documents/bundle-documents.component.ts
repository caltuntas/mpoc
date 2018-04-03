import { Component, OnInit, Input } from "@angular/core";
import { BundleModel } from "../../model/bundle-model";


@Component({
  selector: "app-bundle-documents",
  templateUrl: "./bundle-documents.component.html"
})
export class BundleDocumentsComponent implements OnInit {
  @Input() model: BundleModel;
  constructor(
   
  ) 
  {
   
  }


  ngOnInit() {
    
  }


}
