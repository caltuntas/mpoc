import { productSpecCharValueUseModel } from "./productSpecCharValueUseModel";

export class productSpecCharUseModel {
  constructor(id:number) {
    this.values = new Array<productSpecCharValueUseModel>();
    this.values.push(new productSpecCharValueUseModel());
    this.values.push(new productSpecCharValueUseModel());
    this.values.push(new productSpecCharValueUseModel());
    this.name = 'char use -1';
    this.charType = 'list';
 
    this.id=id;
  }
  public id:number
  public name: string;
  public charType: string;
  public isSelected: boolean;
  public values: Array<productSpecCharValueUseModel>;
}
