export class HomeChartsData {
    public name: string;
    public dataSet: string;
    public labels: string;

    constructor(
        name : string,
        dataSet: string,
        labels : string
    )
    {
        this.name = name;
        this.dataSet = dataSet;
        this.labels = labels;
    }
}
