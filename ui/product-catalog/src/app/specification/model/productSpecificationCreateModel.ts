
import { idNameModel } from "./idNameModel";
import { productSpecCharUseModel } from "./productSpecCharUseModel";

export class productSpecificationCreateModel {
 
 
  public name: string;
  public code: string;
  public description: string;
  // public status: number;
  // public statusList: Array<idNameModel>;
  // public productType: number;
  // public productTypeList: Array<idNameModel>;
  //public isReplicated: boolean;
  public productSpecCharUses: Array<productSpecCharUseModel>;
   
}
