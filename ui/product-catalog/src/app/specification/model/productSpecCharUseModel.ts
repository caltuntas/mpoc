export class productSpecCharUseModel{
    constructor(id:number)
    {
        this.id=id;
        this.values=new Array<number>();
    }
    id:number;
    values:Array<number>;
}