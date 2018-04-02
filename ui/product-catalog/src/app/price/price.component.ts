import {ChangeDetectorRef, Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {DatatableComponent} from '../../shared/ui/datatable/datatable.component';
import {PriceModel} from "./model/priceModel";
import {forEach} from "@angular/router/src/utils/collection";

@Component({
    selector: 'app-price',
    templateUrl: './price.component.html',
    styleUrls: ['./price.component.css']
})
export class PriceComponent implements OnInit {
    priceCurrencyStyle: string;
    isPercentageStyle: string;
    counter: number;
    screenStatus: boolean;

    priceInput: PriceModel = new PriceModel();
    priceList: PriceModel[] = [];

    constructor() {
        this.priceInput.priceType = "OneTime";
        this.priceInput.currency = "TRY";
        this.priceCurrencyStyle = "fa-try"
        this.screenStatus = true; // Add
        this.isPercentageStyle = "fa-money";

        if(this.priceList.length > 0) {
            this.counter = this.priceList.slice(-1)[0].id + 1;
        } else {
            this.counter = 1;
        }

        this.priceList.push({
            id: this.counter++,
            priceType: 'OneTime',
            periodType: 'monthly',
            currency: 'TRY',
            percentage: true,
            amount: 150,
            chargePeriodFrom: 2,
            chargePeriodTo: 5
        });

        this.priceList.push({
            id: this.counter++,
            priceType: 'OneTime',
            periodType: 'monthly',
            currency: 'TRY',
            percentage: true,
            amount: 230,
            chargePeriodFrom: 2,
            chargePeriodTo: 5
        });
    }

    ngOnInit() {
    }

    onChangeCurrency($event) {

        if ($event.target.value == "TRY") {
            this.priceCurrencyStyle = "fa-try";
        }
        else if ($event.target.value == "USD") {
            this.priceCurrencyStyle = "fa-usd";
        }
        else if ($event.target.value == "EUR"){
            this.priceCurrencyStyle = "fa-eur";
        } else {
            //TODO: exceptional cases must be considered
        }
    }

    cleanFields(Ptype) {
        this.priceInput = new PriceModel();
        this.priceInput.priceType = Ptype;
        this.priceInput.currency = "TRY";
        this.isPercentageStyle = "fa-money";

        if(Ptype === "Recurring" || Ptype === "Discount") {
            this.priceInput.periodType = "Daily";
        }
    }

    onChangeIsPercentage() {

        if (this.priceInput.percentage) {
            this.isPercentageStyle = "fa-percent";
        }
        else {
            this.isPercentageStyle = "fa-money";
        }
    }

    addPrice() {
        this.priceList.push({
            id: this.counter++,
            priceType: this.priceInput.priceType,
            periodType: this.priceInput.periodType,
            currency: this.priceInput.currency,
            percentage: this.priceInput.percentage,
            amount: this.priceInput.amount,
            chargePeriodFrom: this.priceInput.chargePeriodFrom,
            chargePeriodTo: this.priceInput.chargePeriodTo
        });

        this.cleanFields("OneTime");
    }

    updatePrice(priceInp) {
        this.priceList[priceInp.id-1] = Object.create(priceInp);
        this.screenStatus = true; // change to add
        this.cleanFields("OneTime");
    }

    onDeletePrice(priceItem) {
        const index: number = this.priceList.indexOf(priceItem);
        if (index !== -1) {
            this.priceList.splice(index, 1);
        }
    }

    onEditPrice(priceItem) {
        this.priceInput = Object.create(priceItem);
        this.screenStatus = false; // change to update
    }

    onCancelUpdate() {
        this.screenStatus = true; // change to add
        this.cleanFields("OneTime");
    }
}
