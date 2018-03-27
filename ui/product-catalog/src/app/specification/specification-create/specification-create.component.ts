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
    this.productSpec=new productSpecificationCreateModel();
  }

  selectedCharUse: number = 0;
  productSpec: productSpecificationCreateModel;
  characteristics: Array<productSpecCharModel>;

  ngOnInit() {
    this.service.getCharacteristics().subscribe((data) => {
      this.characteristics=<Array<productSpecCharModel>>data;
    });
    
   console.log(this.productSpec);
  }

  filterNonSelectedChars(cars: Array<productSpecCharModel>) {
    return cars.filter(x => x.isSelected != true);
  }
  removeCharUse(i: number) {
    let characteristic = this.characteristics[i];
    console.log(i);
    console.log(characteristic);
    characteristic.isSelected = false;
    console.log( this.productSpec.productSpecCharUses);
    this.productSpec.productSpecCharUses=this.productSpec.productSpecCharUses.filter(x => x.id == characteristic.id).slice()
    console.log( this.productSpec.productSpecCharUses);
  }

  selectCharUse($event) {
    this.selectedCharUse = $event.target.value;
    console.log(this.selectedCharUse);
    
  }

  addCharUse() {
    if (this.selectedCharUse != 0) {
      let charUse = this.characteristics.find(x => x.id == this.selectedCharUse);
      charUse.isSelected = true;
      this.productSpec.productSpecCharUses.push(new productSpecCharUseModel(charUse.id));
      console.log(this.productSpec.productSpecCharUses);
    }
  }
  check(characteristic:productSpecCharModel, value: productSpecCharValueModel, $event){
    if($event.target.checked){

      characteristic.values.find(x=>x.id==value.id).isSelected=true;
      let charuse=this.productSpec.productSpecCharUses.find(x => x.id == characteristic.id);
      charuse.values.push(value.id);
      
      
    }
    else{    
      characteristic.values.find(x=>x.id==value.id).isSelected=false;
      this.productSpec.productSpecCharUses.find(x => x.id == characteristic.id).values
      = this.productSpec.productSpecCharUses.find(x => x.id == characteristic.id).values.filter(x=>value.id).slice();
    }
  }

  saveForm(productSpec: productSpecificationCreateModel) {
    console.log(productSpec,"dfg");
  //  this.service.createSpec(productSpec);
  }
}
