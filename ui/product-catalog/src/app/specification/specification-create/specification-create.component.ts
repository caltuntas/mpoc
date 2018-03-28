import { Component, OnInit } from "@angular/core";
import { productSpecificationCreateModel } from "../model/productSpecificationCreateModel";
import { idNameModel } from "../model/idNameModel";
import { productSpecCharUseModel } from "../model/productSpecCharUseModel";
import { productSpecCharModel } from "../model/productSpecCharModel";
import { productSpecCharValueModel } from "../model/productSpecCharValueModel";
import { specificationService } from "../specification.service";
import { Router } from "@angular/router";
@Component({
  selector: "app-specification-create",
  templateUrl: "./specification-create.component.html"
})
export class SpecificationCreateComponent implements OnInit {
  constructor(private router: Router, private service: specificationService) {
    this.productSpec = new productSpecificationCreateModel();
  }

  selectedCharUse: number = 0;
  productSpec: productSpecificationCreateModel;
  characteristics: Array<productSpecCharModel>;

  ngOnInit() {
    this.service.getCharacteristics().subscribe(data => {
      this.characteristics = <Array<productSpecCharModel>>data;
    });
  }

  filterNonSelectedChars(cars: Array<productSpecCharModel>) {
    return cars.filter(x => x.isSelected != true);
  }
  removeCharUse(i: number) {
    let characteristic = this.characteristics[i];
    characteristic.isSelected = false;
    this.productSpec.selectedCharacteristics = this.productSpec.selectedCharacteristics
      .filter(x => x.id == characteristic.id)
      .slice();
  }

  selectCharUse($event) {
    this.selectedCharUse = $event.target.value;
    console.log(this.selectedCharUse);
  }

  addCharUse() {
    if (this.selectedCharUse != 0) {
      let charUse = this.characteristics.find(
        x => x.id == this.selectedCharUse
      );
      charUse.isSelected = true;
      this.productSpec.selectedCharacteristics.push(
        new productSpecCharUseModel(charUse.id)
      );
    }
  }
  check(
    characteristic: productSpecCharModel,
    value: productSpecCharValueModel,
    $event
  ) {
    if ($event.target.checked) {
      characteristic.values.find(x => x.id == value.id).isSelected = true;
      let charuse = this.productSpec.selectedCharacteristics.find(
        x => x.id == characteristic.id
      );
      charuse.selectedValueIds.push(value.id);
    } else {
      characteristic.values.find(x => x.id == value.id).isSelected = false;
      this.productSpec.selectedCharacteristics.find(
        x => x.id == characteristic.id
      ).selectedValueIds = this.productSpec.selectedCharacteristics
        .find(x => x.id == characteristic.id)
        .selectedValueIds.filter(x => value.id)
        .slice();
    }
  }

  saveForm() {
    this.service.createSpec(this.productSpec).subscribe(data => {
      console.log(data);
    });
    this.router.navigate(["/list"]);
  }
}
