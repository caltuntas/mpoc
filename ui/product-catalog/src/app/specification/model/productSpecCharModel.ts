import { productSpecCharValueModel } from "./productSpecCharValueModel";

export class productSpecCharModel {
  
   id:number
   name: string;
   charType: string;
   isSelected:boolean;
   values: Array<productSpecCharValueModel>;
}
