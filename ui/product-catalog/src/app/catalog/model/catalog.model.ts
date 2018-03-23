export class Catalog {
    public id: string;
    public name: string;
    public description: string;
    public deleted: boolean;
    public validForStartDate: string;
    public validForEndDate: string;

    constructor(parameters: { id: string, name: string, description: string, deleted: boolean, validForStartDate: string, validForEndDate: string }) {
        const {id, name, description, deleted, validForStartDate, validForEndDate} = parameters;
        this.id = id;
        this.name = name;
        this.description = description;
        this.deleted = deleted;
        this.validForStartDate = validForStartDate;
        this.validForEndDate = validForEndDate;
    }
}
