export class CreateOfferingModel {
    private name: string;
    private validForStartDate: string;
    private validForEndDate: string;
    private warrantyPeriodValue: number;
    private warrantyPeriodUnit: number;
    private returnPeriodValue: number;
    private returnPeriodUnit: number;
    private description: string;
    private externalId: string;
    private productSpecificationId: number;
    private isReplicated: boolean;
    private isSellable: boolean;

    constructor(name : string,
                validForStartDate: string,
                validForEndDate : string,
                warrantyPeriodValue : number,
                warrantyPeriodUnit : number,
                returnPeriodValue : number,
                returnPeriodUnit : number,
                description : string,
                externalId : string,
                productSpecificationId : number,
                isReplicated : boolean,
                isSellable : boolean
    ){

        this.name = name;
        this.validForStartDate = validForStartDate;
        this.validForEndDate = validForEndDate;
        this.warrantyPeriodUnit = warrantyPeriodUnit;
        this.warrantyPeriodValue = warrantyPeriodValue;
        this.returnPeriodValue = returnPeriodValue;
        this.returnPeriodUnit = returnPeriodUnit;
        this.description = description;
        this.externalId = externalId;
        this.productSpecificationId = productSpecificationId;
        this.isReplicated = isReplicated;
        this.isSellable = isSellable;
    }

}
