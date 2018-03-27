import {OfferingSpecModel} from "./offering-spec-model";

export class Offering {
    public id: string;
    public name: string;
    public description: string;
    public validForStartDate: string;
    public validForEndDate: string;
    public spesification: OfferingSpecModel;
}