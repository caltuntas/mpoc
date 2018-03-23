export class Offering {
    public id : string;
    public name: string;
    public description : string;
    public deleted : boolean;

    constructor(id : string,
                name: string,
                description : string,
                deleted : boolean

    ){
        this.id = id;
        this.name = name;
        this.description = description;
        this.deleted = deleted;
    }
}
