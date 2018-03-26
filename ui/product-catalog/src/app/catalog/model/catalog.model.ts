export class Catalog {
    public id: string;
    public name: string;
    public description: string;
    public deleted: boolean;
    public validForStartDate: Date;
    public validForEndDate: Date;

    constructor(id: string,
                name: string,
                description: string,
                deleted: boolean,
                validForStartDate: Date,
                validForEndDate: Date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deleted = deleted;
        this.validForStartDate = validForStartDate;
        this.validForEndDate =  validForEndDate;
    }
}
