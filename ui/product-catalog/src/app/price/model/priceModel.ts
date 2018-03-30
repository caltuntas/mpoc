export class PriceModel {
    public id: number;
    public priceType: string;
    public periodType: string;
    public percentage: boolean;
    public amount: number;
    public currency: string;
    public changePeriodFrom: number;
    public changePeriodTo: number;
}