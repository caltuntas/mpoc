export class CreateCatalogModel {
    private name: string;
    private validForStartDate: string;
    private validForEndDate: string;
    private description: string;
    private externalId: string;
    private catalogSpecificationId: number;
    private isReplicated: boolean;

    constructor(name: string,
                validForStartDate: string,
                validForEndDate: string,
                description: string,
                externalId: string,
                catalogSpecificationId: number,
                isReplicated: boolean) {

        this.name = name;
        this.validForStartDate = validForStartDate;
        this.validForEndDate = validForEndDate;
        this.description = description;
        this.externalId = externalId;
        this.catalogSpecificationId = catalogSpecificationId;
        this.isReplicated = isReplicated;
    }

}
