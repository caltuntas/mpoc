import {ChangeDetectorRef, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {DatatableComponent} from '../../shared/ui/datatable/datatable.component';

@Component({
    selector: 'app-price',
    templateUrl: './price.component.html',
    styleUrls: ['./price.component.css']
})
export class PriceComponent implements OnInit {
    mySelection: string;
    priceCurrencyStyle: string;
    priceCurrency: number;
    isPercentage: boolean;
    isPercentageStyle: string;

    constructor() {
        this.mySelection = "OneTime";
        this.priceCurrency = 1;
        this.priceCurrencyStyle = "fa-try"
        this.isPercentage = false;
        this.isPercentageStyle = "fa-money";
    }

    ngOnInit() {
        console.log(this.mySelection);
    }

    onChangeCurrency($event) {

        if ($event.target.value == 1) {
            this.priceCurrencyStyle = "fa-try";
        }
        else if ($event.target.value == 2) {
            this.priceCurrencyStyle = "fa-usd";
        }
        else {
            this.priceCurrencyStyle = "fa-eur";
        }
    }

    onChangeIsPercentage() {

        if (this.isPercentage) {
            this.isPercentageStyle = "fa-percent";
        }
        else {
            this.isPercentageStyle = "fa-money";
        }
    }

    public onSubmit() {
        /*this.characteristicService.createCharacteristic(this.model).subscribe(data => {
            console.log("deneme",this.model);
            this.router.navigate(['/characteristic/characteristic-list']);
        });*/
    }

}
