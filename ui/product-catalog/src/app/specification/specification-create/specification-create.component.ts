import { Component, OnInit } from "@angular/core";
import { productSpecificationCreateModel } from "../model/productSpecificationCreateModel";
import { idNameModel } from "../model/idNameModel";
import { productSpecCharUseModel } from "../model/productSpecCharUseModel";
import { productSpecCharModel } from "../model/productSpecCharModel";
import { productSpecCharValueModel } from "../model/productSpecCharValueModel";
import { specificationService } from "../specification.service";

@Component({
  selector: "app-specification-create",
  templateUrl: "./specification-create.component.html"
})
export class SpecificationCreateComponent implements OnInit {
  
  

  constructor(private service: specificationService) {

  }

  selectedCharUse: number = 0;
  productSpec: productSpecificationCreateModel;
  characteristics: Array<productSpecCharModel>;

  ngOnInit() {
    this.service.getCharacteristics().subscribe((data) => {
      this.characteristics=<Array<productSpecCharModel>>data;
    });
    this.characteristics.filter(x => x.isSelected == true).slice();
   
   
  }

  filterNonSelectedChars(cars: Array<productSpecCharModel>) {
    return cars.filter(x => x.isSelected != true);
  }
  removeCharUse(i: number) {
    let characteristic = this.characteristics[i];
    characteristic.isSelected = false;
    this.productSpec.productSpecCharUses
      .filter(x => x.id == characteristic.id)
      .slice();
  }

  selectCharUse($event) {
    this.selectedCharUse = $event.target.value;
  }

  addCharUse() {
    if (this.selectedCharUse != 0) {
      let charUse = this.characteristics.find(
        x => x.id == this.selectedCharUse
      );
      charUse.isSelected = true;
      this.productSpec.productSpecCharUses.push(
        new productSpecCharUseModel(charUse.id)
      );
    }
  }
  check(characteristic:productSpecCharModel, value: productSpecCharValueModel, $event){
    if($event.target.checked){

      characteristic.values.find(x=>x.id==value.id).isSelected=true;
      this.productSpec.productSpecCharUses.find(
        x => x.id == characteristic.id
      ).values.push(value.id);
      
      
    }else{
    
      characteristic.values.find(x=>x.id==value.id).isSelected=false;
      this.productSpec.productSpecCharUses.find(
        x => x.id == characteristic.id
      ).values.filter(x=>value.id).slice();
    }
  }

  save(productSpec: productSpecificationCreateModel) {
    console.log(productSpec);
  }
}
