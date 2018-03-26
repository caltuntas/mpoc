import { productSpecCharValueUseModel } from "./productSpecCharValueUseModel";

export class productSpecCharUseModel {
  constructor() {
    this.values = new Array<productSpecCharValueUseModel>();
    this.values.push(new productSpecCharValueUseModel());
    this.values.push(new productSpecCharValueUseModel());
    this.values.push(new productSpecCharValueUseModel());
    this.name = 'asdfd';
    this.charType = 'list';
    this.isSelected = true;
    this.id=1;
  }
  public id:number
  public name: string;
  public charType: string;
  public isSelected: boolean;
  public values: Array<productSpecCharValueUseModel>;
}
