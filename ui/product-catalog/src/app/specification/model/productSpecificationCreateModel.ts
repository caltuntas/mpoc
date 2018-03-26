import { productSpecCharUseModel } from "./productSpecCharUseModel";
import { idNameModel } from "./idNameModel";

export class productSpecificationCreateModel {
  constructor() {
    this.statusList = new Array<idNameModel>();
    this.productTypeList = new Array<idNameModel>();
    this.productSpecCharUses = new Array<productSpecCharUseModel>();
    this.productSpecCharUses.push(new productSpecCharUseModel());
  }
 ;
  public name: string;
  public code: string;
  public description: string;
  public status: number;
  public statusList: Array<idNameModel>;
  public productType: number;
  public productTypeList: Array<idNameModel>;
  public isReplicated: boolean;
  public productSpecCharUses: Array<productSpecCharUseModel>;
}
