import { Moment } from "moment";

export class CharacteristicCreateModel {
    public id: number;
    public name: string;
    public description: string;
    public valueType: number;
    public validForStartDate: string;
    public validForEndDate: string;
}
