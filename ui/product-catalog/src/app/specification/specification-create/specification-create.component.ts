import { Component, OnInit } from "@angular/core";
import { productSpecificationCreateModel } from "../model/productSpecificationCreateModel";
import { idNameModel } from "../model/idNameModel";
import { productSpecCharUseModel } from "../model/productSpecCharUseModel";

@Component({
  selector: "app-specification-create",
  templateUrl: "./specification-create.component.html"
})
export class SpecificationCreateComponent implements OnInit {
  selectedCharUse: number = 0;
  productSpec: productSpecificationCreateModel;
  constructor() {
    this.productSpec = new productSpecificationCreateModel();
    this.productSpec.statusList.push(new idNameModel(1, "spec -1"));
  }

  ngOnInit() {

    this.productSpec.productSpecCharUses.filter(x=>x.isSelected==true).slice();
  }

  submitted = false;

  onSubmit() {
    this.submitted = true;
    console.log("submitted");
  }
  filterNonSelectedChars(cars:Array<productSpecCharUseModel>) {
    return cars.filter(x=>x.isSelected!=true);
  }
  removeCharUse(i: number) {
    let charUse = this.productSpec.productSpecCharUses[i];
    charUse.isSelected = false;
  }

  selectCharUse($event) {
    this.selectedCharUse = $event.target.value;
    console.log($event.target.value);
  }

  addCharUse() {
    if (this.selectedCharUse != 0) {
      let charUse = this.productSpec.productSpecCharUses.find(x => x.id == this.selectedCharUse);
      charUse.isSelected = true;
    }
  }

  save(productSpec:productSpecificationCreateModel){
console.log(productSpec);
  }
}
