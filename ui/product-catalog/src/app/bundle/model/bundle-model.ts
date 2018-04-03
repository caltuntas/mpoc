export class BundleModel {
  public id: number;
  public name: string;
  public description: string;
  public categoryId: number;  
  public term: number;
  public catalogId: number;
  public warrantyPeriodValue: number;
  public warrantyPeriodUnit: number;
  public returnPeriodValue: number;
  public returnPeriodUnit: number;
  public isSellable: boolean;
  public validForStartDate: string;
  public validForEndDate: string;
  // public prices: Array<number>;
  public saleChannels: Array<number>;
  public segments: Array<number>;
  public documents: Array<number>;
  public offerings: Array<number>;
  public isNewbundle:boolean;

  constructor() {
    this.segments = [];
    this.documents = [];
}

}
