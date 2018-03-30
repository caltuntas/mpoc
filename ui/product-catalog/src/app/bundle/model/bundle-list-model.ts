import {BundleSpecModel} from "./bundle-spec-model";

export class BundleListModel {
    public id: string;
    public name: string;
    public description: string;
    public validForStartDate: string;
    public validForEndDate: string;
    public spesification: BundleSpecModel;
}