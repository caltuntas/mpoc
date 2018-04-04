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
  public salesChannels: Array<number>;
  public segments: Array<number>;
  public documents: Array<number>;
  public simpleProductOfferingIds: Array<number>;
  public isNewbundle: boolean;
  public isReplicated: boolean;
  public productOfferingTypeId: number;

  constructor() {
    this.productOfferingTypeId = 2;
    this.segments = [];
    this.documents = [];
  }

}
