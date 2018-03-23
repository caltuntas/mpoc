import { Component, OnInit } from '@angular/core';
import {Offering} from "../model/offering.model";
import {CreateOfferingModel} from "../model/create-offering-model";
import {OfferingListService} from "./offering-list.service";

@Component({
  selector: 'app-offering-list',
  templateUrl: './offering-list.component.html',
  styleUrls: ['./offering-list.component.css']
})
export class OfferingListComponent implements OnInit {

    public options;
    public offerings: Array<Offering> = [];

    constructor(private offeringListService: OfferingListService) { }

    ngOnInit() {
        this.createOffering();
        this.getOfferings();
    }

    createOffering(){
        let offering =
            new CreateOfferingModel(
                "test",
                "2012-04-23T18:25:43.511Z",
                "2012-04-23T18:25:43.511Z" ,
                1,
                1,
                1,
                1,
                "desc",
                "1",
                1,
                false,
                false
            )

        this.offeringListService.createOffering(offering).subscribe();
    }

    getOfferings() {
        this.offeringListService.getOfferings().subscribe((offerings) => {
            this.offerings = offerings;
            this.options = {
                "data": this.offerings,
                "iDisplayLength": 15,
                "columns": [
                    { "data": "id" },
                    { "data": "name" },
                    { "data": "description" },
                    {"orderable":false,
                        "defaultContent":"   <a (click)='(null)' class='btn btn-primary'><i class='fa fa-edit'></i> Edit</a>"}

                ],
                "order": [[1, 'asc']]
            }
        });
    }

    public lastColumn(d) {

        return "";
    }

}
